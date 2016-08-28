package work.beltran.discogsbrowser.business.wanted;

import rx.Observable;
import rx.functions.Func1;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.UserWanted;
import work.beltran.discogsbrowser.business.ProfileInteractor;
import work.beltran.discogsbrowser.business.RxJavaSchedulers;
import work.beltran.discogsbrowser.business.WantedInteractor;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
public class WantedInteractorImpl implements WantedInteractor {


    private final DiscogsService service;
    private final RxJavaSchedulers schedulers;
    private final ProfileInteractor profileInteractor;

    public WantedInteractorImpl(DiscogsService service,
                                RxJavaSchedulers schedulers,
                                ProfileInteractor profileInteractor) {
        this.service = service;
        this.schedulers = schedulers;
        this.profileInteractor = profileInteractor;
    }

    @Override
    public Observable<UserWanted> getWanted(final int page) {
        return profileInteractor
                .getIdentity()
                .concatMap(new Func1<UserIdentity, Observable<UserWanted>>() {
                    @Override
                    public Observable<UserWanted> call(UserIdentity userIdentity) {
                        return service.getWantedList(userIdentity.getUsername(), page)
                                .subscribeOn(schedulers.io())
                                .observeOn(schedulers.mainThread());
                    }
                });
    }
}
