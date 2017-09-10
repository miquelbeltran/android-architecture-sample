package work.beltran.discogsbrowser.app.common

import work.beltran.discogsbrowser.business.mappers.Mapper
import work.beltran.discogsbrowser.business.model.Record

class RecordToRecordItemMapper: Mapper<Record, RecordItem> {
    override fun mapTo(typeOne: Record): RecordItem {
        return RecordItem(
                title = typeOne.title,
                thumb = typeOne.thumb
        )
    }

    override fun mapFrom(typeTwo: RecordItem): Record {
        throw IllegalAccessException("No need to map from RecordItem to Record")
    }

}