package app.krunal3kapadiya.mlglossary.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var application: Application) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context {
        return application
    }
}