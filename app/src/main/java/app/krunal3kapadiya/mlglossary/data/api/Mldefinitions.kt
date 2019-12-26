package app.krunal3kapadiya.mlglossary.data.api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * response from firebase MLDefinations
 * also used in database DAO object
 */
@Entity(
    tableName = "definitions"
)
data class Mldefinitions
    (
    @ColumnInfo(name = "definition")
    var definition: String = "",
    @ColumnInfo(name = "name")
    var name: String = "",
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)