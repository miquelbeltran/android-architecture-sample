package work.beltran.discogsbrowser.business.old;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.model.SearchResults;
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
                .flatMap(new Func1<UserIdentity, Observable<SearchResults>>() {
                    @Override
                    public Observable<SearchResults> call(UserIdentity userIdentity) {
                        return service.search(query, "release", null, null).subscribeOn(subscribeOnScheduler);
                    }
                })
                .observeOn(observeOnScheduler)
                .flatMap(new Func1<SearchResults, Observable<Record>>() {
                    @Override
                    public Observable<Record> call(SearchResults searchResults) {
                        return Observable.from(searchResults.getRecords());
                    }
                });
    }
}
