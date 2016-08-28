package work.beltran.discogsbrowser.ui.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.currency.FixerService;
import work.beltran.discogsbrowser.currency.FixerServiceBuilder;

/**
 * Created by Miquel Beltran on 05.05.16.
 * More on http://beltran.work
 */
@Module
public class FixerModule {
    private FixerServiceBuilder builder = new FixerServiceBuilder();

    @Provides
    @Singleton
    public FixerService provideFixerService() {
        return builder.provideFixerService();
    }
}
