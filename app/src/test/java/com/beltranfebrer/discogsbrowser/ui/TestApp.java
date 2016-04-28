package com.beltranfebrer.discogsbrowser.ui;

import com.beltranfebrer.discogsbrowser.App;
import com.beltranfebrer.discogsbrowser.api.UserCollection;
import com.beltranfebrer.discogsbrowser.api.di.modules.RecordsAdapterMockModule;
import com.beltranfebrer.discogsbrowser.api.di.modules.UserCollectionMockModule;
import com.beltranfebrer.discogsbrowser.api.di.modules.UserCollectionModule;
import com.beltranfebrer.discogsbrowser.ui.di.DaggerAppComponent;
import com.beltranfebrer.discogsbrowser.ui.di.modules.PicassoModule;
import com.beltranfebrer.discogsbrowser.ui.di.modules.RecordsAdapterModule;

import dagger.Module;

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
        component = DaggerAppComponent
                .builder()
                .recordsAdapterModule(new RecordsAdapterMockModule(mockAdapter))
                .userCollectionModule(new UserCollectionMockModule(mockUserCollection))
                .picassoModule(new PicassoModule(this))
                .build();
    }
}
