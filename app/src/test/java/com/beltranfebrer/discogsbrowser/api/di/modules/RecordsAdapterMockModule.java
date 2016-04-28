package com.beltranfebrer.discogsbrowser.api.di.modules;

import com.beltranfebrer.discogsbrowser.api.UserCollection;
import com.beltranfebrer.discogsbrowser.ui.RecordsAdapter;
import com.beltranfebrer.discogsbrowser.ui.di.modules.RecordsAdapterModule;
import com.squareup.picasso.Picasso;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltranfebrer.com
 */
@Module
public class RecordsAdapterMockModule extends RecordsAdapterModule {
    private RecordsAdapter mockAdapter;

    public RecordsAdapterMockModule(RecordsAdapter mockAdapter) {
        this.mockAdapter = mockAdapter;
    }

    @Override
    public RecordsAdapter providesRecordsAdapter(UserCollection userCollection, Picasso picasso) {
        return mockAdapter;
    }
}
