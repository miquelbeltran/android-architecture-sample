package work.beltran.discogsbrowser.api.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.UserCollection;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
@Module(includes = {DiscogsModule.class})
public class UserCollectionModule {
    private Scheduler observeOnScheduler;
    private Scheduler subscribeOnScheduler;

    public UserCollectionModule(Scheduler observeOnScheduler, Scheduler subscribeOnScheduler) {
        this.observeOnScheduler = observeOnScheduler;
        this.subscribeOnScheduler = subscribeOnScheduler;
    }

    @Provides
    @Singleton
    public UserCollection provideUserCollection(DiscogsService service) {
        return new UserCollection(service, observeOnScheduler, subscribeOnScheduler);
    }
}
