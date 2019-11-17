package app.krunal3kapadiya.mlglossary.di

import app.krunal3kapadiya.mlglossary.MLApplication
import app.krunal3kapadiya.mlglossary.MLDefinitionsAdapter
import app.krunal3kapadiya.mlglossary.MainActivity
import app.krunal3kapadiya.mlglossary.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponents {

    fun inject(mlApplication: MLApplication)
    fun inject(mainActivity: MainActivity)
    fun inject(splashActivity: SplashActivity)
    fun inject(mlDefinitionsViewHolder: MLDefinitionsAdapter.ViewHolder)
}