package work.beltran.discogsbrowser.app.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.DiscogsServiceBuilder;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltran.work
 */
@Module
public class DiscogsModule {

    private DiscogsServiceBuilder builder;

    public DiscogsModule(DiscogsServiceBuilder builder) {
        this.builder = builder;
    }

    @Provides
    @Singleton
    public DiscogsService provideDiscogsService() {
        return builder.provideDiscogsService();
    }
}
