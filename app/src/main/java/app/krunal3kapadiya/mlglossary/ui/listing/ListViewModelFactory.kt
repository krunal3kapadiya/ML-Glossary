package app.krunal3kapadiya.mlglossary.ui.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.krunal3kapadiya.mlglossary.data.db.DefinitionDao

/**
 * Created by Krunal on 12/22/2017.
 */
class ListViewModelFactory(private val dataSource: Any) : ViewModelProvider.Factory {

    /**
     * providing list view model
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListingViewModel::class.java)) {
            return ListingViewModel(dataSource as DefinitionDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}