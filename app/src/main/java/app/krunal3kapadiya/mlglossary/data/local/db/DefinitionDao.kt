package app.krunal3kapadiya.mlglossary.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.krunal3kapadiya.mlglossary.data.api.Mldefinitions
import io.reactivex.Single

@Dao
interface DefinitionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(option: Mldefinitions?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(options: List<Mldefinitions?>?)

    @Query("SELECT * FROM ")
    fun loadAll(): Single<List<Mldefinitions?>?>?
}
