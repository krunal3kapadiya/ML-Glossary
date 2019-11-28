package app.krunal3kapadiya.mlglossary.ui.listing

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import app.krunal3kapadiya.mlglossary.R
import app.krunal3kapadiya.mlglossary.ViewModelFactory
import app.krunal3kapadiya.mlglossary.data.api.Mldefinitions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var context: Context

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val TAG = MainActivity::class.java.simpleName
    var definitionList: ArrayList<Mldefinitions> = ArrayList()

    companion object {
        fun launch(context: Context) {
            val mainIntent = Intent(context, MainActivity::class.java)
            context.startActivity(mainIntent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vf_main.displayedChild = 0


        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("mldefinitions")

        val adapter =
            MLDefinitionsAdapter(
                definitionList
            )

        rv_definitions.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_definitions.adapter = adapter
        val listingViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ListingViewModel::class.java)

        if (isNetworkConnected()) {
            // Read from the database
            vf_main.displayedChild = 1 // displaying loading
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    for (childDataSnapshot in dataSnapshot.children) {
                        val temp = childDataSnapshot.getValue(Mldefinitions::class.java)
                        temp?.let { definitionList.add(it) }
                        vf_main.displayedChild = 0 // displaying list
                    }

                    listingViewModel.insertAll(definitionList)

                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                    vf_main.displayedChild = 0 // displaying list
                }
            })
        } else {
            vf_main.displayedChild = 2 // displaying no internet connection
            bt_internet_on.setOnClickListener {
                this@MainActivity.startActivity(Intent(android.provider.Settings.ACTION_WIFI_SETTINGS))
            }
        }


    }

    // checking internet
    fun isNetworkConnected(): Boolean {
        val cm: ConnectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }
}
