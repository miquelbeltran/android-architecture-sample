package com.beltranfebrer.discogsbrowser.di;

import com.beltranfebrer.discogsbrowser.di.modules.DiscogsModule;
import com.beltranfebrer.discogsbrowser.di.modules.UserCollectionModule;
import com.beltranfebrer.discogsbrowser.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltranfebrer.com
 */
@Singleton
@Component(modules = {UserCollectionModule.class, DiscogsModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
}
