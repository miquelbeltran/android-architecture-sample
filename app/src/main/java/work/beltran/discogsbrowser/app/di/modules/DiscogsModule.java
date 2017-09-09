package work.beltran.discogsbrowser.app.di.modules;

import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.DiscogsServiceBuilder;
import work.beltran.discogsbrowser.api.MoshiProvider;

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
    public Moshi provideGson() {
        return MoshiProvider.INSTANCE.provideMoshi();
    }

    @Provides
    @Reusable
    public DiscogsService provideDiscogsService(Moshi moshi) {
        return builder.provideDiscogsService(moshi);
    }
}
