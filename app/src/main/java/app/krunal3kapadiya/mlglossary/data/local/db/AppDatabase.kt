package app.krunal3kapadiya.mlglossary.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import app.krunal3kapadiya.mlglossary.data.api.Mldefinitions

@Database(entities = [Mldefinitions::class], version = 1)
public abstract class AppDatabase : RoomDatabase() {
    abstract fun definitionDao(): DefinitionDao?
}