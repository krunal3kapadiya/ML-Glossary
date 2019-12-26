package app.krunal3kapadiya.mlglossary

import android.content.Context
import app.krunal3kapadiya.mlglossary.data.db.AppDatabase
import app.krunal3kapadiya.mlglossary.data.db.DefinitionDao
import app.krunal3kapadiya.mlglossary.ui.listing.ListViewModelFactory

object Injection {
    /**
     * list view model
     */
    @JvmStatic
    fun provideListViewModel(context: Context): ListViewModelFactory {
        val definitionDao = provideDefinitionDataSource(context)

        return ListViewModelFactory(definitionDao)
    }

    /**
     * comment data source
     */
    fun provideDefinitionDataSource(context: Context): DefinitionDao {
        val database = AppDatabase.getInstance(context)
        return database.definitionDao()
    }
}
