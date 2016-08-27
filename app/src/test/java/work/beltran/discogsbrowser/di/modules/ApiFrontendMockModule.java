package work.beltran.discogsbrowser.di.modules;


import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.business.profile.ApiFrontend;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
public class ApiFrontendMockModule extends ApiFrontendModule {
    private ApiFrontend apiFrontend;

    public ApiFrontendMockModule(ApiFrontend apiFrontend) {
        super(null, null);
        this.apiFrontend = apiFrontend;
    }

    @Override
    public ApiFrontend provideApiFrontend(DiscogsService service) {
        return apiFrontend;
    }
}
