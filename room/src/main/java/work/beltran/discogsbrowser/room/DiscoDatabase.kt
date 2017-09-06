package work.beltran.discogsbrowser.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(RecordRoom::class), version = 1)
abstract class DiscoDatabase : RoomDatabase() {

    abstract fun recordDao(): RecordDao

    companion object {

        @Volatile private var INSTANCE: DiscoDatabase? = null

        fun getInstance(context: Context): DiscoDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        DiscoDatabase::class.java, "Disco.db")
                        .build()
    }
}