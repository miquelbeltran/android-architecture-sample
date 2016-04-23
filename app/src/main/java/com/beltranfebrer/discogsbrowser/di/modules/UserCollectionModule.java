package com.beltranfebrer.discogsbrowser.di.modules;

import com.beltranfebrer.discogsbrowser.network.DiscogsService;
import com.beltranfebrer.discogsbrowser.UserCollection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltranfebrer.com
 */
@Module(includes = {DiscogsModule.class})
public class UserCollectionModule {
    private String user;

    public UserCollectionModule(String user) {
        this.user = user;
    }

    @Provides
    @Singleton
    public UserCollection provideUserCollection(DiscogsService service) {
        return new UserCollection(service, user, AndroidSchedulers.mainThread());
    }
}
