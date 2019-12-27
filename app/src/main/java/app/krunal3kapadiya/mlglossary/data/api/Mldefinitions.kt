package app.krunal3kapadiya.mlglossary.data.api

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(definition)
        parcel.writeString(name)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Mldefinitions> {
        override fun createFromParcel(parcel: Parcel): Mldefinitions {
            return Mldefinitions(parcel)
        }

        override fun newArray(size: Int): Array<Mldefinitions?> {
            return arrayOfNulls(size)
        }
    }

}