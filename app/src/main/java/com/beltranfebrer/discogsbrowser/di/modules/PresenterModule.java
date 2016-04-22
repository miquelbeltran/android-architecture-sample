package com.beltranfebrer.discogsbrowser.di.modules;

import com.beltranfebrer.discogsbrowser.network.CachedUserCollection;
import com.beltranfebrer.discogsbrowser.ui.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltranfebrer.com
 */
@Module(includes = {DiscogsModule.class})
public class PresenterModule {
    @Provides
    MainActivityPresenter providePresenter(CachedUserCollection api) {
        return new MainActivityPresenter(api);
    }
}
