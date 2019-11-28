package app.krunal3kapadiya.mlglossary.di.component

import android.app.Application
import app.krunal3kapadiya.mlglossary.MLApplication
import app.krunal3kapadiya.mlglossary.ViewModelFactory
import app.krunal3kapadiya.mlglossary.data.local.db.AppDatabase
import app.krunal3kapadiya.mlglossary.di.module.AppModule
import app.krunal3kapadiya.mlglossary.di.module.DBModule
import app.krunal3kapadiya.mlglossary.di.module.ViewModelModule
import app.krunal3kapadiya.mlglossary.ui.SplashActivity
import app.krunal3kapadiya.mlglossary.ui.listing.ListingViewModel
import app.krunal3kapadiya.mlglossary.ui.listing.MLDefinitionsAdapter
import app.krunal3kapadiya.mlglossary.ui.listing.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DBModule::class, ViewModelModule::class])
interface AppComponents {
    fun inject(mlApplication: MLApplication)
    //    fun inject(mainActivity: MainActivity)
    fun inject(splashActivity: SplashActivity)

    fun inject(mlDefinitionsViewHolder: MLDefinitionsAdapter.ViewHolder)

    fun inject(appDatabase: AppDatabase)

//    fun inject(listingViewModel: ListingViewModel)

    fun inject(factory: ViewModelFactory)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponents
    }
}