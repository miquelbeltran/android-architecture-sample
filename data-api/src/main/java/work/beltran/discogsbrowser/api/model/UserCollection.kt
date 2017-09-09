package work.beltran.discogsbrowser.api.model

import com.squareup.moshi.Json
import work.beltran.discogsbrowser.api.model.pagination.Pagination
import work.beltran.discogsbrowser.api.model.record.RecordApi

/**
 * Created by Miquel Beltran on 22.04.16
 * More on http://beltran.work
 */
data class UserCollection(
        override val pagination: Pagination,
        @Json(name = "releases")
        override val records: List<RecordApi>
) : RecordsWithPagination
