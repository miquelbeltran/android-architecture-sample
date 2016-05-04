package work.beltran.discogsbrowser.api;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.subjects.ReplaySubject;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.api.network.CollectionRecordsSubject;
import work.beltran.discogsbrowser.api.network.DiscogsService;
import work.beltran.discogsbrowser.api.network.WantRecordsSubject;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltran.work
 */
public class ApiFrontend {
    private static final String TAG = ApiFrontend.class.getCanonicalName();
    private DiscogsService service;
    private Scheduler observeOnScheduler;
    private Scheduler subscribeOnScheduler;
    private Observable<UserIdentity> userIdentityObservable;
    private WantRecordsSubject wantRecordsSubject;
    private CollectionRecordsSubject collectionRecordsSubject;

    public ApiFrontend(DiscogsService service, Scheduler observeOnScheduler, Scheduler subscribeOnScheduler) {
        this.service = service;
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
        buildUserIndentityRequest();
        wantRecordsSubject = new WantRecordsSubject(service,
                userIdentityObservable,
                subscribeOnScheduler,
                observeOnScheduler);
        collectionRecordsSubject = new CollectionRecordsSubject(service,
                userIdentityObservable,
                subscribeOnScheduler,
                observeOnScheduler);
    }

    private void buildUserIndentityRequest() {
        userIdentityObservable = service.getUserIdentity()
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .cache();
    }

    public Observable<UserIdentity> getUserIdentity() {
        return userIdentityObservable;
    }

    public Observable<UserProfile> getUserProfile() {
        return userIdentityObservable.flatMap(new Func1<UserIdentity, Observable<UserProfile>>() {
            @Override
            public Observable<UserProfile> call(UserIdentity userIdentity) {
                return service.getUserProfile(userIdentity.getUsername())
                        .subscribeOn(subscribeOnScheduler)
                        .observeOn(observeOnScheduler);
            }
        });
    }

    public ReplaySubject<Record> getWantedRecords() {
        return wantRecordsSubject.getSubject();
    }

    public ReplaySubject<Record> getCollectionRecords() {
        return collectionRecordsSubject.getSubject();
    }

    public void loadMoreCollection() {
        collectionRecordsSubject.loadMoreData();
    }

    public void loadMoreWanted() {
        wantRecordsSubject.loadMoreData();
    }
}
