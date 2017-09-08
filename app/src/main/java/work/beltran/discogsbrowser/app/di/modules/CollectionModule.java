package work.beltran.discogsbrowser.app.di.modules;

import dagger.Module;

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
//    @Singleton
//    @Provides
//    public CollectionRepository providesInteractor(DiscogsService service,
//                                                   RxJavaSchedulers schedulers,
//                                                   ProfileInteractor profileInteractor) {
//        return new CollectionInteractorImpl(service, schedulers, profileInteractor);
//    }
//
//    @Provides
//    public CollectionPresenter providesPresenter(CollectionRepository interactor,
//                                                 ProfileInteractor profileInteractor) {
//        return new CollectionPresenter(interactor, profileInteractor);
//    }
//
//    @Provides
//    public ReleasePresenter providesReleasePresenger(CollectionRepository interactor) {
//        return new ReleasePresenter(interactor);
//    }
}
