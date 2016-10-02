package work.beltran.discogsbrowser.business.collection;

import rx.Observable;
import rx.functions.Func1;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.business.CollectionInteractor;
import work.beltran.discogsbrowser.business.ProfileInteractor;
import work.beltran.discogsbrowser.business.RxJavaSchedulers;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class CollectionInteractorImpl implements CollectionInteractor {

    private DiscogsService service;
    private RxJavaSchedulers schedulers;
    private ProfileInteractor profileInteractor;

    public CollectionInteractorImpl(DiscogsService service,
                                    RxJavaSchedulers schedulers,
                                    ProfileInteractor profileInteractor) {
        this.service = service;
        this.schedulers = schedulers;
        this.profileInteractor = profileInteractor;
    }

    @Override
    public Observable<UserCollection> getCollection(final int page) {
        return profileInteractor
                .getIdentity()
                .concatMap(new Func1<UserIdentity, Observable<UserCollection>>() {
                    @Override
                    public Observable<UserCollection> call(UserIdentity userIdentity) {
                        return service.listRecords(userIdentity.getUsername(), page)
                                .subscribeOn(schedulers.io())
                                .observeOn(schedulers.mainThread());
                    }
                });
    }

    @Override
    public Observable<UserCollection> getRecord(final int recordId) {
        return profileInteractor
                .getIdentity()
                .subscribeOn(schedulers.io())
                // Obtain all the instances of a Record
                .concatMap(new Func1<UserIdentity, Observable<UserCollection>>() {
                    @Override
                    public Observable<UserCollection> call(final UserIdentity userIdentity) {
                        return service.getRecordInCollection(
                                userIdentity.getUsername(),
                                recordId);
                    }
                })
                .observeOn(schedulers.mainThread());
    }

    @Override
    public Observable<Void> addRecord(final int recordId) {
        return profileInteractor
                .getIdentity()
                .subscribeOn(schedulers.io())
                .concatMap(new Func1<UserIdentity, Observable<Void>>() {
                    @Override
                    public Observable<Void> call(final UserIdentity userIdentity) {
                        return service.addToCollection(userIdentity.getUsername(),
                                recordId);
                    }
                })
                .observeOn(schedulers.mainThread());
    }

    @Override
    public Observable<Void> removeRecord(final int recordId) {
        // Obtain the profile user name
        return profileInteractor
                .getIdentity()
                .subscribeOn(schedulers.io())
                // Obtain all the instances of a Record
                .concatMap(new Func1<UserIdentity, Observable<UserCollection>>() {
                    @Override
                    public Observable<UserCollection> call(final UserIdentity userIdentity) {
                        return service.getRecordInCollection(userIdentity.getUsername(),
                                recordId);
                    }
                })
                // emit each Record
                .flatMapIterable(new Func1<UserCollection, Iterable<Record>>() {
                    @Override
                    public Iterable<Record> call(UserCollection userCollection) {
                        return userCollection.getRecords();
                    }
                })
                // delete each record
                .flatMap(new Func1<Record, Observable<Void>>() {
                    @Override
                    public Observable<Void> call(final Record record) {
                        return profileInteractor
                                .getIdentity()
                                .concatMap(new Func1<UserIdentity, Observable<Void>>() {
                                    @Override
                                    public Observable<Void> call(UserIdentity userIdentity) {
                                        return service.removeFromCollection(
                                                userIdentity.getUsername(),
                                                record.getId(),
                                                record.getInstanceId());
                                    }
                                });
                    }
                })
                .observeOn(schedulers.mainThread());
    }
}
