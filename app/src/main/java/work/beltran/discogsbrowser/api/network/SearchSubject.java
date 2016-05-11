package work.beltran.discogsbrowser.api.network;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import work.beltran.discogsbrowser.api.model.Results;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.record.Record;

/**
 * Created by Miquel Beltran on 10.05.16.
 * More on http://beltran.work
 */
public class SearchSubject {
    private final DiscogsService service;
    private final Scheduler subscribeOnScheduler;
    private final Scheduler observeOnScheduler;
    private final Observable<UserIdentity> userIdentityObservable;

    public SearchSubject(DiscogsService service,
                         Observable<UserIdentity> userIdentityObservable,
                         Scheduler subscribeOnScheduler,
                         Scheduler observeOnScheduler) {
        this.service = service;
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
        this.userIdentityObservable = userIdentityObservable;
    }

    public Observable<Record> search(final String query, final int nextPage) {
        return userIdentityObservable
                .flatMap(new Func1<UserIdentity, Observable<Results>>() {
                    @Override
                    public Observable<Results> call(UserIdentity userIdentity) {
                        return service.search(query, "release", null, null).subscribeOn(subscribeOnScheduler);
                    }
                })
                .observeOn(observeOnScheduler)
                .flatMap(new Func1<Results, Observable<Record>>() {
                    @Override
                    public Observable<Record> call(Results results) {
                        return Observable.from(results.getRecords());
                    }
                });
    }
}
