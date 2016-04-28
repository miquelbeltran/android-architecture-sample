package com.beltranfebrer.discogsbrowser.ui.di;

import com.beltranfebrer.discogsbrowser.api.di.modules.UserCollectionModule;
import com.beltranfebrer.discogsbrowser.ui.MainActivity;
import com.beltranfebrer.discogsbrowser.ui.RecordsAdapter;
import com.beltranfebrer.discogsbrowser.ui.di.modules.PicassoModule;
import com.beltranfebrer.discogsbrowser.ui.di.modules.RecordsAdapterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltranfebrer.com
 */
@Singleton
@Component(modules = {RecordsAdapterModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
}
