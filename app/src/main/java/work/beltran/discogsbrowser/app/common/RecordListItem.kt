package work.beltran.discogsbrowser.app.common

import work.beltran.discogsbrowser.api.model.record.Record

data class RecordListItem(
        val title: CharSequence,
        val artist: CharSequence,
        val format: CharSequence,
        val year: CharSequence,
        val thumb: String,
        val releaseId: Int,
        val inCollection: Boolean
)

