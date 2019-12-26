package app.krunal3kapadiya.mlglossary


import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber

class MLApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            Timber.plant(Timber.DebugTree())
        }
    }
}