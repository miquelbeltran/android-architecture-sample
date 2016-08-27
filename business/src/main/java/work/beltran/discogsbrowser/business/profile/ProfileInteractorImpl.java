package work.beltran.discogsbrowser.business.profile;

import rx.Observable;
import rx.functions.Func1;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.business.ProfileInteractor;
import work.beltran.discogsbrowser.business.RxJavaSchedulers;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class ProfileInteractorImpl implements ProfileInteractor {
    private DiscogsService service;
    private RxJavaSchedulers schedulers;

    public ProfileInteractorImpl(DiscogsService service, RxJavaSchedulers schedulers) {
        this.service = service;
        this.schedulers = schedulers;
    }

    @Override
    public Observable<UserIdentity> getIdentity() {
        return service.getUserIdentity()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread());
    }

    @Override
    public Observable<UserProfile> getProfile() {
        return service.getUserIdentity()
                .concatMap(new Func1<UserIdentity, Observable<UserProfile>>() {
                    @Override
                    public Observable<UserProfile> call(UserIdentity userIdentity) {
                        return service.getUserProfile(userIdentity.getUsername())
                                .subscribeOn(schedulers.io())
                                .observeOn(schedulers.mainThread());
                    }
                });
    }
}
