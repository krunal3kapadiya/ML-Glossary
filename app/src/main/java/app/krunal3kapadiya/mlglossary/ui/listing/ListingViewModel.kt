package app.krunal3kapadiya.mlglossary.ui.listing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.krunal3kapadiya.mlglossary.data.api.Mldefinitions
import app.krunal3kapadiya.mlglossary.data.db.DefinitionDao
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ListingViewModel(private val definitionDao: DefinitionDao) : ViewModel() {
    var disposable: CompositeDisposable = CompositeDisposable()
    var definitionList: MutableLiveData<ArrayList<Mldefinitions>> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var isError: MutableLiveData<Boolean> = MutableLiveData()

    fun insertAll(mlDefinitionsList: ArrayList<Mldefinitions>): Completable {
        return Completable.fromAction {
            definitionDao.insertAll(definitionList = mlDefinitionsList)
        }
    }

    fun onFirebaseApiCallAndAddIntoDatabase() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("mldefinitions")
        isLoading.postValue(true)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mlList = ArrayList<Mldefinitions>()
                for (childDataSnapshot in dataSnapshot.children) {
                    val temp = childDataSnapshot.getValue(Mldefinitions::class.java)
                    temp?.let { mlList.add(it) }
                }

                disposable.add(
                    insertAll(mlList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
                )

                fetchDataFromDatabase()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                isLoading.postValue(false)
                isError.postValue(true)
                Timber.tag("Failed to read value.".plus(error.toException()))
            }
        })
    }

    fun fetchDataFromDatabase() {
        isLoading.postValue(true)
        disposable.add(
            definitionDao.loadAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when {
                        it.isNotEmpty() -> {
                            definitionList.postValue(it as ArrayList<Mldefinitions>)
                            isError.postValue(false)
                            isLoading.postValue(false)
                        }
                        else -> {
                            isError.postValue(true)
                            isLoading.postValue(false)
                        }
                    }
                },
                    {
                        isError.postValue(true)
                        isLoading.postValue(false)
                    })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}