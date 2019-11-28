package app.krunal3kapadiya.mlglossary.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.krunal3kapadiya.mlglossary.ViewModelFactory
import app.krunal3kapadiya.mlglossary.di.ViewModelKey
import app.krunal3kapadiya.mlglossary.ui.listing.ListingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListingViewModel::class)
    abstract fun bindListingViewModel(listingViewModel: ListingViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
