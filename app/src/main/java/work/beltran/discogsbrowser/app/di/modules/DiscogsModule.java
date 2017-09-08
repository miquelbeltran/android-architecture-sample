package work.beltran.discogsbrowser.app.di.modules;

import dagger.Module;
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

//    @Provides
//    @Singleton
//    public Gson provideGson() {
//        return GsonProvider.INSTANCE.provideGson();
//    }
//
//    @Provides
//    @Singleton
//    public DiscogsService provideDiscogsService(Gson gson) {
//        return builder.provideDiscogsService(gson);
//    }
}
