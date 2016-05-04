package work.beltran.discogsbrowser.api.di.modules;

import work.beltran.discogsbrowser.api.ApiFrontend;
import work.beltran.discogsbrowser.api.DiscogsService;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
public class UserCollectionMockModule extends UserCollectionModule {
    private ApiFrontend apiFrontend;

    public UserCollectionMockModule(ApiFrontend apiFrontend) {
        super(null, null);
        this.apiFrontend = apiFrontend;
    }

    @Override
    public ApiFrontend provideUserCollection(DiscogsService service) {
        return apiFrontend;
    }
}
