package app.krunal3kapadiya.mlglossary.data.local.db

import androidx.room.*
import app.krunal3kapadiya.mlglossary.data.api.Mldefinitions
import io.reactivex.Single

@Dao
interface DefinitionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(option: Mldefinitions?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(options: List<Mldefinitions?>?)

    @Query("SELECT * FROM definitions")
    fun loadAll(): Single<List<Mldefinitions?>?>?

    @Delete
    fun delete(mldefinitions: Mldefinitions)
}
