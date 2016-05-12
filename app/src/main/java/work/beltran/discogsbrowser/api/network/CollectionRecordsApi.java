package work.beltran.discogsbrowser.api.network;

import rx.Observable;
import rx.Scheduler;
import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.api.model.UserIdentity;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
public class CollectionRecordsApi extends RecordsApi<UserCollection> {
    public CollectionRecordsApi(DiscogsService service,
                                Observable<UserIdentity> userIdentityObservable,
                                Scheduler subscribeOnScheduler,
                                Scheduler observeOnScheduler) {
        super(service, userIdentityObservable, subscribeOnScheduler, observeOnScheduler);
    }

    @Override
    protected Observable<UserCollection> serviceCallToGetRecords(UserIdentity userIdentity, int nextPage) {
        return service.listRecords(userIdentity.getUsername(), nextPage);
    }
}
