package work.beltran.discogsbrowser.app.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.app.wantlist.WantlistPresenter;
import work.beltran.discogsbrowser.business.ProfileInteractor;
import work.beltran.discogsbrowser.business.RxJavaSchedulers;
import work.beltran.discogsbrowser.business.WantedInteractor;
import work.beltran.discogsbrowser.business.wanted.WantedInteractorImpl;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
@Module(includes = {
        DiscogsModule.class,
        RxJavaSchedulersModule.class,
        ProfileModule.class
})
public class WantlistModule {
    @Singleton
    @Provides
    public WantedInteractor providesInteractor(DiscogsService service,
                                               RxJavaSchedulers schedulers,
                                               ProfileInteractor profileInteractor) {
        return new WantedInteractorImpl(service, schedulers, profileInteractor);
    }

    @Provides
    public WantlistPresenter providesPresenter(WantedInteractor interactor,
                                               ProfileInteractor profileInteractor) {
        return new WantlistPresenter(interactor, profileInteractor);
    }
}
