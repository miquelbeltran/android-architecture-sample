package work.beltran.discogsbrowser.ui;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.DiscogsServiceBuilderWithKey;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.business.old.ApiFrontend;
import work.beltran.discogsbrowser.di.modules.ApiFrontendMockModule;
import work.beltran.discogsbrowser.ui.di.modules.AveragePriceModule;
import work.beltran.discogsbrowser.ui.di.modules.DiscogsModule;
import work.beltran.discogsbrowser.ui.di.DaggerApiComponent;
import work.beltran.discogsbrowser.ui.di.DaggerAppComponent;
import work.beltran.discogsbrowser.ui.di.DaggerLoginComponent;
import work.beltran.discogsbrowser.ui.di.modules.ContextModule;
import work.beltran.discogsbrowser.ui.di.modules.LoginMockModule;
import work.beltran.discogsbrowser.ui.di.modules.SettingsMockModule;
import work.beltran.discogsbrowser.ui.main.wantlist.WantRecordsAdapterOld;
import work.beltran.discogsbrowser.ui.settings.Settings;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
public class TestApp extends App {
    public static ApiFrontend mockApiFrontend;
    public static Settings mockSettings;
    public static WantRecordsAdapterOld mockWant;

    @Override
    public void onCreate() {
        super.onCreate();
        mockWant = mock(WantRecordsAdapterOld.class);
        mockApiFrontend = mock(ApiFrontend.class);
        when(mockApiFrontend.getUserProfile()).thenReturn(rx.Observable.just(new UserProfile()));
        mockSettings = mock(Settings.class);
        appComponent = DaggerAppComponent
                .builder()
                .settingsModule(new SettingsMockModule(mockSettings))
                .contextModule(new ContextModule(this))
                .build();
        loginComponent = DaggerLoginComponent
                .builder()
                .loginPresenterModule(new LoginMockModule())
                .contextModule(new ContextModule(this))
                .build();
        apiComponent = DaggerApiComponent
                .builder()
                .contextModule(new ContextModule(this))
                .discogsModule(new DiscogsModuleWithKey())
                .apiFrontendModule(new ApiFrontendMockModule(mockApiFrontend))
                .averagePriceModule(new AveragePriceModule(Schedulers.io(), AndroidSchedulers.mainThread()))
                .build();
    }

    private class DiscogsModuleWithKey extends DiscogsModule {
        public DiscogsModuleWithKey() {
            super(null);
        }

        DiscogsServiceBuilderWithKey builderWithKey = new DiscogsServiceBuilderWithKey(BuildConfig.API_KEY, "Unit Tests");

        @Override
        public DiscogsService provideDiscogsService() {
            return builderWithKey.provideDiscogsService();
        }
    }
}
