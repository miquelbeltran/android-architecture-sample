package work.beltran.discogsbrowser.api.model

import work.beltran.discogsbrowser.api.model.pagination.Pagination
import work.beltran.discogsbrowser.api.model.record.Record

/**
 * Created by Miquel Beltran on 22.04.16
 * More on http://beltran.work
 */
data class UserCollection(
        override val pagination: Pagination,
        override val records: List<Record>
) : RecordsWithPagination
