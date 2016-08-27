package work.beltran.discogsbrowser.di.modules;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.LoginServiceBuilder;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltran.work
 */
@Module
public class LoginModule {

    private LoginServiceBuilder builder;

    public LoginModule(LoginServiceBuilder builder) {
        this.builder = builder;
    }

    @Provides
    @Singleton
    public DiscogsService provideDiscogsService() {
        builder.provideLoginService();
    }
}
