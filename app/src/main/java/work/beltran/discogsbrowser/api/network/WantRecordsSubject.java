package work.beltran.discogsbrowser.api.network;

import rx.Observable;
import rx.Scheduler;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.UserWanted;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
public class WantRecordsSubject extends RecordsSubject<UserWanted> {

    public WantRecordsSubject(DiscogsService service,
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
