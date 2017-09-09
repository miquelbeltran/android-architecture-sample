package work.beltran.discogsbrowser.business

import io.reactivex.Completable
import io.reactivex.Flowable
import work.beltran.discogsbrowser.api.model.record.RecordApi
import work.beltran.discogsbrowser.business.model.Record

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
interface CollectionRepository {
    fun getAllRecords(): Flowable<List<Record>>
    fun fetchRecords(page: Int): Completable
    fun getRecord(recordId: Int): Flowable<Record>
    fun fetchRecord(recordId: Int): Completable
    fun addRecord(recordId: Int): Completable
    fun removeRecord(recordId: Int): Completable
}
