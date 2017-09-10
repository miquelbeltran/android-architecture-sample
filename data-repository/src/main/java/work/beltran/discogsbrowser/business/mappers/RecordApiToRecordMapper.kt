package work.beltran.discogsbrowser.business.mappers

import work.beltran.discogsbrowser.api.model.record.RecordApi
import work.beltran.discogsbrowser.business.model.Record

class RecordApiToRecordMapper : Mapper<RecordApi, Record> {
    override fun mapTo(typeOne: RecordApi): Record {
        return Record(
                id = typeOne.id,
                title = typeOne.basicInformation.title,
                thumb = typeOne.basicInformation.thumb
        )
    }

    override fun mapFrom(typeTwo: Record): RecordApi {
        throw IllegalAccessException("No need to map from Record to RecordApi")
    }
}