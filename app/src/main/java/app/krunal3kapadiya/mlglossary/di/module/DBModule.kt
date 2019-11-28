package app.krunal3kapadiya.mlglossary.di.module

import android.content.Context
import androidx.room.Room
import app.krunal3kapadiya.mlglossary.data.local.db.AppDatabase
import app.krunal3kapadiya.mlglossary.data.local.db.DefinitionDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {
    @Singleton
    @Provides
    fun provideDBModuleDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideMLDefinitionDao(appDatabase: AppDatabase): DefinitionDao? {
        return appDatabase.definitionDao()
    }
}