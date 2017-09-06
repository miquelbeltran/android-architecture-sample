package work.beltran.discogsbrowser.room

import io.reactivex.Flowable
import work.beltran.discogsbrowser.business.ReactiveStore

class RoomReactiveStore(private val database: DiscoDatabase) : ReactiveStore<Int, RecordRoom> {
    override fun getAll(): Flowable<List<RecordRoom>> = database.recordDao().getAll()

    override fun replace(list: List<RecordRoom>) {
        database.recordDao().replace(list)
    }

    override fun remove(value: RecordRoom) {
        database.recordDao().remove(value)
    }

    override fun get(id: Int): Flowable<RecordRoom> = database.recordDao().get(id)
}

