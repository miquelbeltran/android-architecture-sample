package work.beltran.discogsbrowser.business.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.di.DiscogsModule;
import work.beltran.discogsbrowser.business.AveragePrice;
import work.beltran.discogsbrowser.currency.FixerService;
import work.beltran.discogsbrowser.currency.di.FixerModule;

/**
 * Created by Miquel Beltran on 05.05.16.
 * More on http://beltran.work
 */
@Module(includes = {DiscogsModule.class, FixerModule.class})
public class AveragePriceModule {
    private Scheduler observeOnScheduler;
    private Scheduler subscribeOnScheduler;

    public AveragePriceModule(Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
    }

    @Provides
    @Singleton
    public AveragePrice providesAveragePrice(DiscogsService service, FixerService fixerService) {
        return new AveragePrice(service, fixerService, subscribeOnScheduler, observeOnScheduler);
    }
}
