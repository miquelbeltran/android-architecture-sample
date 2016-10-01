package work.beltran.discogsbrowser.app.di.modules;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.DiscogsServiceBuilder;
import work.beltran.discogsbrowser.api.GsonProvider;

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
    public Gson provideGson() {
        return GsonProvider.provideGson();
    }

    @Provides
    @Singleton
    public DiscogsService provideDiscogsService(Gson gson) {
        return builder.provideDiscogsService(gson);
    }
}
