package work.beltran.discogsbrowser.app;

import android.app.Application;
import android.support.annotation.Nullable;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.api.DiscogsServiceBuilder;
import work.beltran.discogsbrowser.api.LoginServiceBuilder;
import work.beltran.discogsbrowser.app.di.ApiComponent;
import work.beltran.discogsbrowser.app.di.AppComponent;
import work.beltran.discogsbrowser.app.di.DaggerApiComponent;
import work.beltran.discogsbrowser.app.di.DaggerAppComponent;
import work.beltran.discogsbrowser.app.di.DaggerLoginComponent;
import work.beltran.discogsbrowser.app.di.LoginComponent;
import work.beltran.discogsbrowser.app.di.modules.ContextModule;
import work.beltran.discogsbrowser.app.di.modules.DiscogsModule;
import work.beltran.discogsbrowser.app.di.modules.LoginModule;
import work.beltran.discogsbrowser.business.settings.Settings;

/**
 * Created by Miquel Beltran on 22.04.16.
 */
@SuppressWarnings("ALL")
public class App extends Application {
    private static final String TAG = App.class.getCanonicalName();
    /* package */ @Nullable ApiComponent apiComponent;
    /* package */ AppComponent appComponent;
    /* package */ LoginComponent loginComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .contextModule(new ContextModule(this))
                .build();
        loginComponent = DaggerLoginComponent
                .builder()
                .contextModule(new ContextModule(this))
                .loginModule(
                        new LoginModule(
                                new LoginServiceBuilder(
                                        BuildConfig.APPLICATION_ID+"/"+BuildConfig.VERSION_NAME)))
                .build();
        Settings settings = appComponent.getSettings();
        if (!settings.getUserToken().isEmpty() && !settings.getUserSecret().isEmpty()) {
            initApiComponent(settings.getUserToken(), settings.getUserSecret());
        }
    }

    @Nullable
    public ApiComponent getApiComponent() {
        return apiComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public LoginComponent getLoginComponent() {
        return loginComponent;
    }

    public void initApiComponent(String userToken, String userSecret) {
        apiComponent = DaggerApiComponent
                .builder()
                .contextModule(new ContextModule(this))
                .discogsModule(
                        new DiscogsModule(
                                new DiscogsServiceBuilder(
                                        BuildConfig.API_CONSUMER_KEY,
                                        BuildConfig.API_CONSUMER_SECRET,
                                        userToken,
                                        userSecret,
                                        BuildConfig.APPLICATION_ID+"/"+BuildConfig.VERSION_NAME)))
                .build();
    }
}

