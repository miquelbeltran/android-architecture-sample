package work.beltran.discogsbrowser.api;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.subjects.ReplaySubject;
import work.beltran.discogsbrowser.api.model.Record;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.UserProfile;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltran.work
 */
public class UserCollection {
    private static final String TAG = UserCollection.class.getCanonicalName();
    private DiscogsService service;
    private Scheduler observeOnScheduler;
    private Scheduler subscribeOnScheduler;
    private Observable<UserIdentity> userIdentityObservable;
    private WantRecordsList wantRecordsList;
    private CollectionRecordsList collectionRecordsList;

    public UserCollection(DiscogsService service, Scheduler observeOnScheduler, Scheduler subscribeOnScheduler) {
        this.service = service;
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
        buildUserIndentityRequest();
        wantRecordsList = new WantRecordsList(service, userIdentityObservable, subscribeOnScheduler, observeOnScheduler);
        collectionRecordsList = new CollectionRecordsList(service, userIdentityObservable, subscribeOnScheduler, observeOnScheduler);
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
                return service.getUserProfile(userIdentity.getUsername()).subscribeOn(subscribeOnScheduler).observeOn(observeOnScheduler);
            }
        });
    }

    public ReplaySubject<Record> getWantedRecords() {
        return wantRecordsList.getSubject();
    }

    public ReplaySubject<Record> getCollectionRecords() {
        return collectionRecordsList.getSubject();
    }

    public void loadMoreCollection() {
        collectionRecordsList.loadMoreData();
    }

    public void loadMoreWanted() {
        wantRecordsList.loadMoreData();
    }
}
