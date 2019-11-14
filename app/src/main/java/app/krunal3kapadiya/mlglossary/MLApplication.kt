package app.krunal3kapadiya.mlglossary

import android.app.Application
import app.krunal3kapadiya.mlglossary.di.AppComponents
import app.krunal3kapadiya.mlglossary.di.AppModule
import app.krunal3kapadiya.mlglossary.di.DaggerAppComponents

class MLApplication : Application() {

    companion object {
        lateinit var appComponent:AppComponents
        lateinit var instance:MLApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
        appComponent=createAppComponent()
        appComponent.inject(this)
    }

    private fun createAppComponent():AppComponents{
        return DaggerAppComponents.builder().appModule(AppModule(this)).build()
    }
}