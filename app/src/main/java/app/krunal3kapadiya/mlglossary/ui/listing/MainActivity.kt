package app.krunal3kapadiya.mlglossary.ui.listing

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import app.krunal3kapadiya.mlglossary.Injection
import app.krunal3kapadiya.mlglossary.R
import app.krunal3kapadiya.mlglossary.data.api.Mldefinitions
import app.krunal3kapadiya.mlglossary.ui.AboutActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

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

        val adapter =
            MLDefinitionsAdapter(
                definitionList
            )

        rv_definitions.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_definitions.adapter = adapter

        val viewModelFactory = Injection.provideListViewModel(this)
        val listingViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ListingViewModel::class.java)

        listingViewModel.isLoading.observe(this, Observer {
            when {
                it -> {
                    vf_main.displayedChild = 1 // displaying loading
                }
                else -> {
                    vf_main.displayedChild = 0 // displaying list
                }
            }
        })

        listingViewModel.isError.observe(this, Observer {
            when {
                it -> {
                    vf_main.displayedChild = 2 // displaying no internet connection
                    bt_internet_on.setOnClickListener {
                        this@MainActivity.startActivity(Intent(android.provider.Settings.ACTION_WIFI_SETTINGS))
                    }
                }
            }
        })

        listingViewModel.definitionList.observe(this, Observer {
            definitionList.clear()
            definitionList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        when {
            isNetworkConnected() -> {
                // if internet is connected load api call
                listingViewModel.onFirebaseApiCallAndAddIntoDatabase()
            }
            else -> {
                listingViewModel.fetchDataFromDatabase()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                AboutActivity.launch(this@MainActivity)
            }
        }
        return true
    }
}
