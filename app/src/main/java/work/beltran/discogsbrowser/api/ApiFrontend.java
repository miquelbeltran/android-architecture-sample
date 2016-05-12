package work.beltran.discogsbrowser.api;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.api.network.CollectionRecordsApi;
import work.beltran.discogsbrowser.api.network.DiscogsService;
import work.beltran.discogsbrowser.api.network.SearchSubject;
import work.beltran.discogsbrowser.api.network.WantRecordsApi;

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
    private WantRecordsApi wantRecordsSubject;
    private CollectionRecordsApi collectionRecordsSubject;
    private Observable<UserProfile> userProfileObservable;

    public ApiFrontend(DiscogsService service, Scheduler observeOnScheduler, Scheduler subscribeOnScheduler) {
        this.service = service;
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
        buildUserIndentityRequest();
        buildUserProfileRequest();
        wantRecordsSubject = new WantRecordsApi(service,
                userIdentityObservable,
                subscribeOnScheduler,
                observeOnScheduler);
        collectionRecordsSubject = new CollectionRecordsApi(service,
                userIdentityObservable,
                subscribeOnScheduler,
                observeOnScheduler);
    }

    private void buildUserProfileRequest() {
        userProfileObservable = userIdentityObservable.flatMap(new Func1<UserIdentity, Observable<UserProfile>>() {
            @Override
            public Observable<UserProfile> call(UserIdentity userIdentity) {
                return service.getUserProfile(userIdentity.getUsername())
                        .subscribeOn(subscribeOnScheduler)
                        .observeOn(observeOnScheduler);
            }
        }).cache();
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
        return userProfileObservable;
    }

    public WantRecordsApi getWantedRecords() {
        return wantRecordsSubject;
    }

    public CollectionRecordsApi getCollectionRecords() {
        return collectionRecordsSubject;
    }

    public SearchSubject getSearchSubject() {
        return new SearchSubject(service, userIdentityObservable, subscribeOnScheduler, observeOnScheduler);
    }
}
