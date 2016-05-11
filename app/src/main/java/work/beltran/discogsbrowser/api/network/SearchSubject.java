package work.beltran.discogsbrowser.api.network;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;
import work.beltran.discogsbrowser.api.model.Results;
import work.beltran.discogsbrowser.api.model.UserIdentity;

/**
 * Created by Miquel Beltran on 10.05.16.
 * More on http://beltran.work
 */
public class SearchSubject extends RecordsSubject<Results> {
    private BehaviorSubject<String> searchQueries;

    public SearchSubject(DiscogsService service,
                         Observable<UserIdentity> userIdentityObservable,
                         Scheduler subscribeOnScheduler,
                         Scheduler observeOnScheduler) {
        super(service, userIdentityObservable, subscribeOnScheduler, observeOnScheduler);
        searchQueries = BehaviorSubject.create();
    }

    public void search(String query) {
        loadMoreData();
        searchQueries.onNext(query);
    }

    @Override
    protected Observable<Results> serviceCallToGetRecords(UserIdentity userIdentity, int nextPage) {
        return searchQueries
                .asObservable()
                .flatMap(new Func1<String, Observable<Results>>() {
                    @Override
                    public Observable<Results> call(String s) {
                        return service.search(s).subscribeOn(subscribeOnScheduler);
                    }
                });
    }
}
