package work.beltran.discogsbrowser.business;

import rx.Observable;
import rx.Scheduler;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.UserWanted;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
public class WantRecordsApi extends RecordsApi<UserWanted> {

    public WantRecordsApi(DiscogsService service,
                          Observable<UserIdentity> userIdentityObservable,
                          Scheduler subscribeOnScheduler,
                          Scheduler observeOnScheduler) {
        super(service, userIdentityObservable, subscribeOnScheduler, observeOnScheduler);
    }

    @Override
    protected Observable<UserWanted> serviceCallToGetRecords(UserIdentity userIdentity, int nextPage) {
        return service.getWantedList(userIdentity.getUsername(), nextPage);
    }
}
