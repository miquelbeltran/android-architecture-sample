package work.beltran.discogsbrowser.app.common

import work.beltran.discogsbrowser.api.model.record.Record

fun Record.toListItem(inCollection: Boolean): RecordListItem =
        RecordListItem(
                artist = basicInformation.artists.joinToString(),
                format = basicInformation.formats.joinToString(),
                inCollection = inCollection,
                releaseId = id,
                thumb = basicInformation.thumb,
                title = basicInformation.title,
                year = when (basicInformation.year) {
                    "0" -> ""
                    else -> basicInformation.year
                }
        )

fun List<Record>.toListItem(inCollection: Boolean): List<RecordListItem> =
        map { it.toListItem(inCollection) }
