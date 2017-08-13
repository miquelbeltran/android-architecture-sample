package work.beltran.discogsbrowser.api.model

import com.squareup.moshi.Json
import work.beltran.discogsbrowser.api.model.pagination.Pagination
import work.beltran.discogsbrowser.api.model.record.Artist
import work.beltran.discogsbrowser.api.model.record.BasicInformation
import work.beltran.discogsbrowser.api.model.record.Format
import work.beltran.discogsbrowser.api.model.record.Record

/**
 * Created by Miquel Beltran on 10.05.16.
 * More on http://beltran.work
 */
data class SearchResults(
        override val pagination: Pagination,
        @Json(name = "results")
        val searchRecords: List<SearchRecord>
) : RecordsWithPagination {
    override val records: List<Record>
        get() = searchRecords.map {
            var artist = it.title
            var title = ""
            val index = it.title.indexOf(" - ")
            if (index > 0) {
                title = it.title.substring(index + 3)
                artist = it.title.substring(0, index)
            }
            val formats = it.format?.map { Format(it) } ?: emptyList()
            val basicInformation = BasicInformation(
                    artists = listOf(Artist(artist)),
                    formats = formats,
                    title = title,
                    thumb = it.thumb,
                    year = it.year ?: ""
            )
            Record(it.id, null, basicInformation)
        }
}
