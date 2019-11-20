package app.krunal3kapadiya.mlglossary

import android.app.Application
import app.krunal3kapadiya.mlglossary.di.component.AppComponents
import app.krunal3kapadiya.mlglossary.di.component.DaggerAppComponents
import app.krunal3kapadiya.mlglossary.di.module.AppModule

class MLApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponents
        lateinit var instance: MLApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = createAppComponent()
        appComponent.inject(this)
    }

    private fun createAppComponent(): AppComponents {
        return DaggerAppComponents.builder()
            .appModule(AppModule(this))
            .build()
    }
}