package work.beltran.discogsbrowser.business

import io.reactivex.Single
import work.beltran.discogsbrowser.api.model.record.RecordApi

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
interface SearchInteractor {
    fun search(query: String): Single<List<RecordApi>>
}
