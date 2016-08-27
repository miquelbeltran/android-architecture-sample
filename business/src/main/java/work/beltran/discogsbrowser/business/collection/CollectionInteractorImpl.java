package work.beltran.discogsbrowser.business.collection;

import rx.Observable;
import rx.functions.Func1;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.api.model.UserIdentity;
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
}
