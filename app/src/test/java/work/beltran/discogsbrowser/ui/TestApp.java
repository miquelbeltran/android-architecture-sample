package work.beltran.discogsbrowser.ui;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.api.ApiFrontend;
import work.beltran.discogsbrowser.api.di.modules.ApiFrontendMockModule;
import work.beltran.discogsbrowser.api.di.modules.AveragePriceModule;
import work.beltran.discogsbrowser.api.di.modules.DiscogsModuleWithApiKey;
import work.beltran.discogsbrowser.api.di.modules.LoginModule;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.ui.di.DaggerApiComponent;
import work.beltran.discogsbrowser.ui.di.DaggerAppComponent;
import work.beltran.discogsbrowser.ui.di.DaggerLoginComponent;
import work.beltran.discogsbrowser.ui.di.modules.ContextModule;
import work.beltran.discogsbrowser.ui.di.modules.LoginMockModule;
import work.beltran.discogsbrowser.ui.di.modules.RecordsAdapterMockModule;
import work.beltran.discogsbrowser.ui.di.modules.SettingsMockModule;
import work.beltran.discogsbrowser.ui.main.collection.CollectionRecordsAdapter;
import work.beltran.discogsbrowser.ui.main.common.UserRecordsAdapter;
import work.beltran.discogsbrowser.ui.main.wantlist.WantRecordsAdapter;
import work.beltran.discogsbrowser.ui.settings.Settings;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
public class TestApp extends App {
    private static CollectionRecordsAdapter mockAdapter;
    public static ApiFrontend mockApiFrontend;
    public static Settings mockSettings;
    public static WantRecordsAdapter mockWant;

    public static UserRecordsAdapter getMockAdapter() {
        return mockAdapter;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mockAdapter = mock(CollectionRecordsAdapter.class);
        mockWant = mock(WantRecordsAdapter.class);
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
                .loginModule(new LoginMockModule())
                .contextModule(new ContextModule(this))
                .build();
        apiComponent = DaggerApiComponent
                .builder()
                .contextModule(new ContextModule(this))
                .discogsModule(new DiscogsModuleWithApiKey(BuildConfig.API_KEY))
                .recordsAdapterModule(new RecordsAdapterMockModule(mockAdapter, mockWant))
                .apiFrontendModule(new ApiFrontendMockModule(mockApiFrontend))
                .averagePriceModule(new AveragePriceModule(Schedulers.io(), AndroidSchedulers.mainThread()))
                .build();
    }
}
