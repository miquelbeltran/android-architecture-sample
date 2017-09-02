package work.beltran.discogsbrowser.business

import io.reactivex.Completable
import io.reactivex.Single
import work.beltran.discogsbrowser.api.model.UserCollection

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
interface CollectionInteractor {
    fun getCollection(page: Int): Single<UserCollection>
    fun getRecord(recordId: Int): Single<UserCollection>
    fun addRecord(recordId: Int): Completable
    fun removeRecord(recordId: Int): Completable
}
