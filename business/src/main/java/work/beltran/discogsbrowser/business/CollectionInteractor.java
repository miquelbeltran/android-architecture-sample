package work.beltran.discogsbrowser.business;

import io.reactivex.Completable;
import io.reactivex.Single;
import work.beltran.discogsbrowser.api.model.UserCollection;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public interface CollectionInteractor {
    Single<UserCollection> getCollection(int page);
    Single<UserCollection> getRecord(int recordId);
    Completable addRecord(int recordId);
    Completable removeRecord(int recordId);
}
