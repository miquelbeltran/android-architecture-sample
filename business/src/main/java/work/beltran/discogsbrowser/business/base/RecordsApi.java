package work.beltran.discogsbrowser.business.base;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.model.RecordsWithPagination;
import work.beltran.discogsbrowser.api.model.UserIdentity;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
public abstract class RecordsApi<T extends RecordsWithPagination> {
    private Scheduler observeOnScheduler;
    protected Scheduler subscribeOnScheduler;
    protected DiscogsService service;
    private Observable<UserIdentity> userIdentityObservable;

    protected RecordsApi(DiscogsService service,
                         Observable<UserIdentity> userIdentityObservable,
                         Scheduler subscribeOnScheduler,
                         Scheduler observeOnScheduler) {
        this.service = service;
        this.userIdentityObservable = userIdentityObservable;
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
    }

    public Observable<T> getRecordsFromService(final int nextPage) {
        return userIdentityObservable
                .flatMap(new Func1<UserIdentity, Observable<T>>() {
                    @Override
                    public Observable<T> call(UserIdentity userIdentity) {
                        return serviceCallToGetRecords(userIdentity, nextPage)
                                .subscribeOn(subscribeOnScheduler);
                    }
                })
                .observeOn(observeOnScheduler);
    }

    protected abstract Observable<T> serviceCallToGetRecords(UserIdentity userIdentity, int nextPage);
}
