package work.beltran.discogsbrowser.room

import io.reactivex.Flowable
import work.beltran.discogsbrowser.business.ReactiveStore
import work.beltran.discogsbrowser.business.model.Record
import work.beltran.discogsbrowser.business.mappers.Mapper

class RoomReactiveStore(private val database: DiscoDatabase,
                        private val mapper: Mapper<Record, RecordRoom>) : ReactiveStore<Int, Record> {

    override fun getAll(): Flowable<List<Record>> = database.recordDao().getAll().map(mapper::mapFrom)

    override fun replace(value: Record) {
        database.recordDao().replace(mapper.mapTo(value))
    }

    override fun replace(list: List<Record>) {
        database.recordDao().replace(mapper.mapTo(list))
    }

    override fun remove(value: Record) {
        database.recordDao().remove(mapper.mapTo(value))
    }

    override fun get(id: Int): Flowable<Record> = database.recordDao().get(id).map(mapper::mapFrom)
}

