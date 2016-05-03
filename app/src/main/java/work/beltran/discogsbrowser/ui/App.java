package work.beltran.discogsbrowser.ui;

import android.app.Application;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import work.beltran.discogsbrowser.api.di.modules.DiscogsModule;
import work.beltran.discogsbrowser.api.di.modules.UserCollectionModule;
import work.beltran.discogsbrowser.ui.di.ApiComponent;
import work.beltran.discogsbrowser.ui.di.AppComponent;
import work.beltran.discogsbrowser.ui.di.DaggerApiComponent;
import work.beltran.discogsbrowser.ui.di.DaggerAppComponent;
import work.beltran.discogsbrowser.ui.di.modules.ContextModule;

/**
 * Created by Miquel Beltran on 22.04.16.
 */
@SuppressWarnings("ALL")
public class App extends Application {
    private static final String TAG = App.class.getCanonicalName();
    /* package */ ApiComponent apiComponent;
    /* package */ AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public void setApiKey(String apiKey) {
        apiComponent = DaggerApiComponent
                .builder()
                .contextModule(new ContextModule(this))
                .discogsModule(new DiscogsModule(apiKey))
                .userCollectionModule(new UserCollectionModule(AndroidSchedulers.mainThread(), Schedulers.io()))
                .build();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

