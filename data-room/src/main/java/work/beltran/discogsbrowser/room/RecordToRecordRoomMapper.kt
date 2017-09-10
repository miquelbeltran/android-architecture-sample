package work.beltran.discogsbrowser.room

import work.beltran.discogsbrowser.business.mappers.Mapper
import work.beltran.discogsbrowser.business.model.Record

class RecordToRecordRoomMapper: Mapper<Record, RecordRoom> {
    override fun mapTo(typeOne: Record): RecordRoom {
        return RecordRoom(
                id = typeOne.id,
                title = typeOne.title,
                year = "",
                thumb = typeOne.thumb
        )
    }

    override fun mapFrom(typeTwo: RecordRoom): Record {
        return Record(
                id = typeTwo.id,
                title = typeTwo.title,
                thumb = typeTwo.thumb
        )
    }
}