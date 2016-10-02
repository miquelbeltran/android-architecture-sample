package work.beltran.discogsbrowser.app.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.app.release.ReleasePresenter;
import work.beltran.discogsbrowser.business.CollectionInteractor;
import work.beltran.discogsbrowser.business.ProfileInteractor;
import work.beltran.discogsbrowser.business.RxJavaSchedulers;
import work.beltran.discogsbrowser.business.collection.CollectionInteractorImpl;
import work.beltran.discogsbrowser.app.collection.CollectionPresenter;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
@Module(includes = {
        DiscogsModule.class,
        RxJavaSchedulersModule.class,
        ProfileModule.class
})
public class CollectionModule {
    @Singleton
    @Provides
    public CollectionInteractor providesInteractor(DiscogsService service,
                                                   RxJavaSchedulers schedulers,
                                                   ProfileInteractor profileInteractor) {
        return new CollectionInteractorImpl(service, schedulers, profileInteractor);
    }

    @Provides
    public CollectionPresenter providesPresenter(CollectionInteractor interactor,
                                                 ProfileInteractor profileInteractor) {
        return new CollectionPresenter(interactor, profileInteractor);
    }

    @Provides
    public ReleasePresenter providesReleasePresenger(CollectionInteractor interactor) {
        return new ReleasePresenter(interactor);
    }
}
