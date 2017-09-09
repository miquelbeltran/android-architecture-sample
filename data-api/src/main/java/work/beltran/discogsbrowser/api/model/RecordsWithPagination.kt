package work.beltran.discogsbrowser.api.model

import work.beltran.discogsbrowser.api.model.pagination.Pagination
import work.beltran.discogsbrowser.api.model.record.RecordApi

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
interface RecordsWithPagination {
    val pagination: Pagination
    val records: List<RecordApi>
}
