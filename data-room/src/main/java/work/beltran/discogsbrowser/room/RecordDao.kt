package work.beltran.discogsbrowser.room

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface RecordDao {
    @Query("SELECT * FROM record")
    fun getAll(): Flowable<List<RecordRoom>>

    @Query("SELECT * FROM record WHERE id == :id")
    fun get(id: Int): Flowable<RecordRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun replace(list: List<RecordRoom>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun replace(value: RecordRoom)

    @Delete
    fun remove(value: RecordRoom)
}