package work.beltran.discogsbrowser.business.collection

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import work.beltran.discogsbrowser.api.DiscogsService
import work.beltran.discogsbrowser.api.model.UserCollection
import work.beltran.discogsbrowser.business.CollectionInteractor
import work.beltran.discogsbrowser.business.RxJavaSchedulers

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
class CollectionInteractorImpl(private val service: DiscogsService,
                               private val schedulers: RxJavaSchedulers,
                               private val username: String)
    : CollectionInteractor {

    override fun getCollection(page: Int): Single<UserCollection> {
        return service.listRecords(username, page)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
    }

    override fun getRecord(recordId: Int): Single<UserCollection> {
        return service.getRecordInCollection(username, recordId)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
    }

    override fun addRecord(recordId: Int): Completable {
        return service.addToCollection(username, recordId)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
    }

    override fun removeRecord(recordId: Int): Completable {
        // Obtain the profile user name
        return service.getRecordInCollection(username, recordId)
                // emit each Record
                .map { it.records }
                // delete each record
                .flatMapCompletable {
                    Observable.fromIterable(it)
                            .flatMapCompletable {
                                service.removeFromCollection(
                                        username,
                                        it.id,
                                        it.instanceId!!)
                            }
                }
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
    }
}
