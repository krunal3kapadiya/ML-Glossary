package app.krunal3kapadiya.mlglossary.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.krunal3kapadiya.mlglossary.data.api.Mldefinitions

@Database(entities = [Mldefinitions::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun definitionDao(): DefinitionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * fetching single instance of post database
         */
        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        /**
         * creating database
         */
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app.db"
        ).build()
    }

}