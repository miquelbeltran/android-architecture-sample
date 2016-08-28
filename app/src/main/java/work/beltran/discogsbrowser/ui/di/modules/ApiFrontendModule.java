package work.beltran.discogsbrowser.ui.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.business.old.ApiFrontend;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
@Module(includes = {DiscogsModule.class})
public class ApiFrontendModule {
    private Scheduler subscribeOnScheduler;
    private Scheduler observeOnScheduler;

    public ApiFrontendModule(Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
    }

    @Provides
    @Singleton
    public ApiFrontend provideApiFrontend(DiscogsService service) {
        return new ApiFrontend(service, observeOnScheduler, subscribeOnScheduler);
    }
}
