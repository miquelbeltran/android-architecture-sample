package com.beltranfebrer.discogsbrowser.ui.di;

import com.beltranfebrer.discogsbrowser.api.di.modules.UserCollectionModule;
import com.beltranfebrer.discogsbrowser.ui.RecordsAdapter;
import com.beltranfebrer.discogsbrowser.ui.di.modules.PicassoModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltranfebrer.com
 */
@Singleton
@Component(modules = {UserCollectionModule.class, PicassoModule.class})
public interface AppComponent {
    void inject(RecordsAdapter adapter);
}
