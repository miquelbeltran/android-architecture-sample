package work.beltran.discogsbrowser.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "record")
data class RecordRoom(
        @PrimaryKey
        val id: Int,
        val title: String,
        val year: String,
        val thumb: String
)