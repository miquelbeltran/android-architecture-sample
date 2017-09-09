package work.beltran.discogsbrowser.app.common

import work.beltran.discogsbrowser.app.common.RecordListItem
import work.beltran.discogsbrowser.business.mappers.Mapper
import work.beltran.discogsbrowser.business.model.Record

class RecordToRecordItemMapper: Mapper<Record, RecordListItem> {
    override fun mapTo(typeOne: Record): RecordListItem {
        return RecordListItem(
                title = typeOne.title
        )
    }

    override fun mapFrom(typeTwo: RecordListItem): Record {
        throw IllegalAccessException("No need to map from RecordItem to Record")
    }

}