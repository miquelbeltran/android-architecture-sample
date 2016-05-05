package work.beltran.discogsbrowser.ui;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.api.ApiFrontend;
import work.beltran.discogsbrowser.api.di.modules.DiscogsModule;
import work.beltran.discogsbrowser.api.di.modules.UserCollectionMockModule;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.ui.main.collection.CollectionRecordsAdapter;
import work.beltran.discogsbrowser.ui.main.common.RecordsAdapter;
import work.beltran.discogsbrowser.ui.di.DaggerApiComponent;
import work.beltran.discogsbrowser.ui.di.DaggerAppComponent;
import work.beltran.discogsbrowser.ui.di.modules.ContextModule;
import work.beltran.discogsbrowser.ui.di.modules.RecordsAdapterMockModule;
import work.beltran.discogsbrowser.ui.di.modules.SettingsMockModule;
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

    public static RecordsAdapter getMockAdapter() {
        return mockAdapter;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mockAdapter = mock(CollectionRecordsAdapter.class);
        mockApiFrontend = mock(ApiFrontend.class);
        when(mockApiFrontend.getUserProfile()).thenReturn(rx.Observable.just(new UserProfile()));
        mockSettings = mock(Settings.class);
        appComponent = DaggerAppComponent
                .builder()
                .settingsModule(new SettingsMockModule(mockSettings))
                .contextModule(new ContextModule(this))
                .build();
        apiComponent = DaggerApiComponent
                .builder()
                .contextModule(new ContextModule(this))
                .discogsModule(new DiscogsModule(BuildConfig.API_KEY))
                .recordsAdapterModule(new RecordsAdapterMockModule(mockAdapter))
                .userCollectionModule(new UserCollectionMockModule(mockApiFrontend))
                .build();
    }
}
