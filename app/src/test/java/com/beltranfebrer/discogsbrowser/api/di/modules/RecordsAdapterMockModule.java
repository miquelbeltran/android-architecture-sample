package com.beltranfebrer.discogsbrowser.api.di.modules;

import com.beltranfebrer.discogsbrowser.ui.RecordsAdapter;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltranfebrer.com
 */
@Module
public class RecordsAdapterMockModule {
    @Provides
    public RecordsAdapter providesRecordsAdapter() {
        return Mockito.mock(RecordsAdapter.class);
    }
}
