package work.beltran.discogsbrowser.ui;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.api.UserCollection;
import work.beltran.discogsbrowser.api.di.modules.DiscogsModule;
import work.beltran.discogsbrowser.api.di.modules.RecordsAdapterMockModule;
import work.beltran.discogsbrowser.api.di.modules.UserCollectionMockModule;

import work.beltran.discogsbrowser.ui.collection.RecordsAdapter;
import work.beltran.discogsbrowser.ui.di.ApiComponent;
import work.beltran.discogsbrowser.ui.di.DaggerApiComponent;
import work.beltran.discogsbrowser.ui.di.modules.ContextModule;
import work.beltran.discogsbrowser.ui.di.modules.PicassoModule;

import static org.mockito.Mockito.mock;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltranfebrer.com
 */
public class TestApp extends App {
    private static RecordsAdapter mockAdapter;
    private static UserCollection mockUserCollection;

    public static RecordsAdapter getMockAdapter() {
        return mockAdapter;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mockAdapter = mock(RecordsAdapter.class);
        mockUserCollection = mock(UserCollection.class);
        apiComponent = DaggerApiComponent
                .builder()
                .contextModule(new ContextModule(this))
                .discogsModule(new DiscogsModule(BuildConfig.API_KEY))
                .recordsAdapterModule(new RecordsAdapterMockModule(mockAdapter))
                .userCollectionModule(new UserCollectionMockModule(mockUserCollection))
                .build();
    }
}
