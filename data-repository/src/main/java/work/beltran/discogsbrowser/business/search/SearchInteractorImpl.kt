package work.beltran.discogsbrowser.business.search

import io.reactivex.Single
import work.beltran.discogsbrowser.api.DiscogsService
import work.beltran.discogsbrowser.api.model.record.RecordApi
import work.beltran.discogsbrowser.business.RxJavaSchedulers
import work.beltran.discogsbrowser.business.SearchInteractor

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
class SearchInteractorImpl(private val service: DiscogsService,
                           private val schedulers: RxJavaSchedulers)
    : SearchInteractor {

    override fun search(query: String): Single<List<RecordApi>> {
        return service.search(query, SEARCH_TYPE, null, null)
                .subscribeOn(schedulers.io())
                .map { it.records }
                .observeOn(schedulers.mainThread())
    }

    companion object {
        const val SEARCH_TYPE = "release"
    }
}
