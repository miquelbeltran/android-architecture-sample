package com.beltranfebrer.discogsbrowser;

import android.app.Application;
import android.util.Log;

import com.beltranfebrer.discogsbrowser.di.AppComponent;
import com.beltranfebrer.discogsbrowser.di.DaggerAppComponent;

/**
 * Created by Miquel Beltran on 22.04.16.
 */
public class App extends Application {
    private static final String TAG = App.class.getCanonicalName();
    private static AppComponent component;

        @Override
        public void onCreate() {
            super.onCreate();
            Log.d(TAG, "App::onCreate()");
            component = DaggerAppComponent.create();
        }

        public static AppComponent getComponent() {
            return component;
        }
}

