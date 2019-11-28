package app.krunal3kapadiya.mlglossary

import android.app.Application
import app.krunal3kapadiya.mlglossary.di.component.AppComponents
import app.krunal3kapadiya.mlglossary.di.component.DaggerAppComponents
import app.krunal3kapadiya.mlglossary.di.module.AppModule
import com.facebook.stetho.Stetho

class MLApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponents
        lateinit var instance: MLApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
        appComponent = createAppComponent()

//        appComponent = DaggerAppComponents.builder()
//            .application(this)
//            .build()

        appComponent.inject(this)
    }

    private fun createAppComponent(): AppComponents {
        return DaggerAppComponents.builder()
            .build()
    }
}