package work.beltran.discogsbrowser.business;

import rx.Observable;
import work.beltran.discogsbrowser.api.model.UserCollection;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public interface CollectionInteractor {
    Observable<UserCollection> getCollection(int page);

    Observable<UserCollection> getRecord(int recordId);

    Observable<Void> addRecord(int recordId);

    Observable<Void> removeRecord(int recordId);
}
