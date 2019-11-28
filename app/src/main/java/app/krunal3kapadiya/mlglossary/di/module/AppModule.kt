package app.krunal3kapadiya.mlglossary.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import app.krunal3kapadiya.mlglossary.data.local.db.AppDatabase
import app.krunal3kapadiya.mlglossary.di.DatabaseInfo
import app.krunal3kapadiya.mlglossary.di.PreferenceInfo
import app.krunal3kapadiya.mlglossary.utils.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
object AppModule {
    @JvmStatic
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }
}

//@Module
//class AppModule(var application: Application) {
//
//    @Provides
//    @Singleton
//    fun providesApplicationContext(): Context {
//        return application
//    }
//
//    @Provides
//    @Singleton
//    fun provideAppDatabase(@DatabaseInfo dbName: String?, context: Context?): AppDatabase? {
//        return Room.databaseBuilder(context!!, AppDatabase::class.java, dbName!!)
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//
//    @Provides
//    @PreferenceInfo
//    fun providePreferenceName(): String? {
//        return AppConstants.PREF_NAME
//    }
//}