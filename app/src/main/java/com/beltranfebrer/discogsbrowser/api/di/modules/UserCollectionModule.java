package com.beltranfebrer.discogsbrowser.api.di.modules;

import com.beltranfebrer.discogsbrowser.api.DiscogsService;
import com.beltranfebrer.discogsbrowser.api.UserCollection;
import com.beltranfebrer.discogsbrowser.api.di.modules.DiscogsModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltranfebrer.com
 */
@Module(includes = {DiscogsModule.class})
public class UserCollectionModule {
    private String user;
    private Scheduler observeOnScheduler;
    private Scheduler subscribeOnScheduler;

    public UserCollectionModule(String user,Scheduler observeOnScheduler, Scheduler subscribeOnScheduler) {
        this.user = user;
        this.observeOnScheduler = observeOnScheduler;
        this.subscribeOnScheduler = subscribeOnScheduler;
    }

    @Provides
    @Singleton
    public UserCollection provideUserCollection(DiscogsService service) {
        return new UserCollection(service, user, observeOnScheduler, subscribeOnScheduler);
    }
}
