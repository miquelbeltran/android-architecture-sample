package com.beltranfebrer.discogsbrowser;

import android.app.Application;

import com.beltranfebrer.discogsbrowser.ui.di.AppComponent;
import com.beltranfebrer.discogsbrowser.ui.di.DaggerAppComponent;
import com.beltranfebrer.discogsbrowser.api.di.modules.UserCollectionModule;
import com.beltranfebrer.discogsbrowser.ui.di.modules.PicassoModule;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Miquel Beltran on 22.04.16.
 */
@SuppressWarnings("ALL")
public class App extends Application {
    private static final String TAG = App.class.getCanonicalName();
    private static AppComponent component;

        @Override
        public void onCreate() {
            super.onCreate();
            component = DaggerAppComponent
                    .builder()
                    .userCollectionModule(new UserCollectionModule("mike513", AndroidSchedulers.mainThread(), Schedulers.io()))
                    .picassoModule(new PicassoModule(this))
                    .build();
        }

        public static AppComponent getComponent() {
            return component;
        }
}

