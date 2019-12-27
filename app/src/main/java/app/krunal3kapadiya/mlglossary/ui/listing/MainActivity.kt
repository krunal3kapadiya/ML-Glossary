package app.krunal3kapadiya.mlglossary.ui.listing

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import app.krunal3kapadiya.mlglossary.BuildConfig
import app.krunal3kapadiya.mlglossary.Injection
import app.krunal3kapadiya.mlglossary.R
import app.krunal3kapadiya.mlglossary.base.SharedPrefsProvider
import app.krunal3kapadiya.mlglossary.base.SharedPrefsProvider.Companion.USER_PREF_FILE
import app.krunal3kapadiya.mlglossary.data.api.Mldefinitions
import app.krunal3kapadiya.mlglossary.ui.AboutActivity
import app.krunal3kapadiya.mlglossary.ui.detail.DetailDialogFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), OnClickListener {

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

        // show first time dialog
        showInfoDialog()

        vf_main.displayedChild = 0

        val adapter =
            MLDefinitionsAdapter(
                this,
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

    private fun showInfoDialog() {
        val showDisplayDialog = SharedPrefsProvider(
            applicationContext.getSharedPreferences(
                USER_PREF_FILE,
                Context.MODE_PRIVATE
            )
        ).getBoolFromPreferences(getString(R.string.pref_display_dialog), true)

        if (showDisplayDialog) {
            AlertDialog.Builder(this).apply {
                setTitle(getString(R.string.app_name).plus(" " + BuildConfig.VERSION_NAME))
                setMessage(getString(R.string.message_dialog))
            }.setPositiveButton(getString(R.string.okay)) { dialog, _ ->
                dialog.dismiss()
            }.setNegativeButton(
                getString(R.string.never_show)
            ) { _, _ ->
                SharedPrefsProvider(
                    applicationContext.getSharedPreferences(
                        USER_PREF_FILE,
                        Context.MODE_PRIVATE
                    )
                ).savePreferences(getString(R.string.pref_display_dialog), false)
            }.create().show()
        }
    }

    override fun onClickListener(position: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val prev = supportFragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            fragmentTransaction.remove(prev)
        }
        fragmentTransaction.addToBackStack(null)
        val dialogFragment = DetailDialogFragment.newInstance(definitionList[position])
        dialogFragment.show(fragmentTransaction, "dialog")
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
