package app.krunal3kapadiya.mlglossary.ui.listing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.krunal3kapadiya.mlglossary.data.api.Mldefinitions
import app.krunal3kapadiya.mlglossary.data.local.db.MLDefinitionsRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ListingViewModel : ViewModel() {
    lateinit var mlDefinitionsRepository: MLDefinitionsRepository
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var liveData: MutableLiveData<ArrayList<Mldefinitions>>

    @Inject
    fun ListingViewModel(mlDefinitionsRepository: MLDefinitionsRepository) {
        this.mlDefinitionsRepository = mlDefinitionsRepository
        this.compositeDisposable = CompositeDisposable()
        this.liveData = MutableLiveData()
    }

    fun insertAll(mldefinitions: ArrayList<Mldefinitions>) {
        mlDefinitionsRepository.insertAll(mldefinitions)
    }

}