package work.beltran.discogsbrowser.ui.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.business.ProfileInteractor;
import work.beltran.discogsbrowser.business.RxJavaSchedulers;
import work.beltran.discogsbrowser.business.profile.ProfileInteractorImpl;
import work.beltran.discogsbrowser.di.modules.DiscogsModule;
import work.beltran.discogsbrowser.di.modules.RxJavaSchedulersModule;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
@Module(includes = {
        DiscogsModule.class,
        RxJavaSchedulersModule.class
})
public class ProfileModule {
    @Singleton
    @Provides
    public ProfileInteractor providesInteractor(DiscogsService service,
                                                RxJavaSchedulers schedulers) {
        return new ProfileInteractorImpl(service, schedulers);
    }
}
