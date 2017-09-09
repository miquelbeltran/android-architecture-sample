package work.beltran.discogsbrowser.api.model

import com.squareup.moshi.Json
import work.beltran.discogsbrowser.api.model.pagination.Pagination
import work.beltran.discogsbrowser.api.model.record.RecordApi

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
data class UserWanted(
        @Json(name = "pagination")
        override val pagination: Pagination,
        @Json(name = "wants")
        override val records: List<RecordApi>
) : RecordsWithPagination
