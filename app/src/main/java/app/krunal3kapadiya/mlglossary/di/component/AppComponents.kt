package app.krunal3kapadiya.mlglossary.di.component

import app.krunal3kapadiya.mlglossary.MLApplication
import app.krunal3kapadiya.mlglossary.di.module.AppModule
import app.krunal3kapadiya.mlglossary.ui.SplashActivity
import app.krunal3kapadiya.mlglossary.ui.listing.MLDefinitionsAdapter
import app.krunal3kapadiya.mlglossary.ui.listing.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponents {
    fun inject(mlApplication: MLApplication)
    fun inject(mainActivity: MainActivity)
    fun inject(splashActivity: SplashActivity)
    fun inject(mlDefinitionsViewHolder: MLDefinitionsAdapter.ViewHolder)
}