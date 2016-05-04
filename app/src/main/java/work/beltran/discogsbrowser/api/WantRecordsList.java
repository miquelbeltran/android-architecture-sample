package work.beltran.discogsbrowser.api;

import rx.Observable;
import rx.Scheduler;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.WantedList;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
public class WantRecordsList extends RecordsList<WantedList> {

    protected WantRecordsList(DiscogsService service,
                              Observable<UserIdentity> userIdentityObservable,
                              Scheduler subscribeOnScheduler,
                              Scheduler observeOnScheduler) {
        super(service, userIdentityObservable, subscribeOnScheduler, observeOnScheduler);
    }

    @Override
    protected Observable<WantedList> serviceCallToGetRecords(UserIdentity userIdentity, int nextPage) {
        return service.getWantedList(userIdentity.getUsername(), nextPage);
    }
}
