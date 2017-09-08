package work.beltran.discogsbrowser.business.collection

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import work.beltran.discogsbrowser.api.DiscogsService
import work.beltran.discogsbrowser.api.model.UserCollection
import work.beltran.discogsbrowser.api.model.record.Record
import work.beltran.discogsbrowser.business.CollectionRepository
import work.beltran.discogsbrowser.business.ReactiveStore
import work.beltran.discogsbrowser.business.RxJavaSchedulers
import work.beltran.discogsbrowser.business.SettingsRepository

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
class CollectionRepositoryImpl(private val service: DiscogsService,
                               private val schedulers: RxJavaSchedulers,
                               private val store: ReactiveStore<Int, Record>,
                               private val username: String)
    : CollectionRepository {

    override fun getRecord(recordId: Int): Flowable<Record> = store.get(recordId)

    override fun getAllRecords(): Flowable<List<Record>> = store.getAll()

    override fun fetchRecords(page: Int): Completable =
            service.listRecords(username, page).storeResponse()

    override fun fetchRecord(recordId: Int): Completable =
            service.getRecordInCollection(username, recordId).storeResponse()

    override fun addRecord(recordId: Int): Completable =
            service.addToCollection(username, recordId)
                    .andThen(fetchRecord(recordId))
                    .subscribeOn(schedulers.io())

    override fun removeRecord(recordId: Int): Completable =
            service.getRecordInCollection(username, recordId)
            .map { it.records }
            .flatMapCompletable {
                Observable.fromIterable(it)
                        .flatMapCompletable {
                            store.remove(it)
                            service.removeFromCollection(
                                    username,
                                    it.id,
                                    it.instanceId!!)
                        }
            }
            .subscribeOn(schedulers.io())

    private fun Single<UserCollection>.storeResponse(): Completable =
            subscribeOn(schedulers.io())
                .observeOn(schedulers.computation())
                .doOnSuccess { store.replace(it.records) }
                .toCompletable()
}

