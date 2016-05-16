package work.beltran.discogsbrowser.api.di.modules;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.api.RxJavaSchedulers;
import work.beltran.discogsbrowser.api.network.DiscogsService;
import work.beltran.discogsbrowser.ui.login.LoginPresenter;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltran.work
 */
@Module
public class LoginModule {
    public static final String BASE_URL = "https://api.discogs.com/";

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("User-Agent", BuildConfig.APPLICATION_ID)
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });


//        httpClient.addInterceptor(new Interceptor() {
//            @Override public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//
//                long t1 = System.nanoTime();
//                Log.d("OkHttp", String.format("Sending request %s on %s%n%s",
//                        request.url(), chain.connection(), request.headers()));
//
//                Response response = chain.proceed(request);
//
//                long t2 = System.nanoTime();
//                Log.d("OkHttp", String.format("Received response for %s in %.1fms%n%s",
//                        response.request().url(), (t2 - t1) / 1e6d, response.headers()));
//
//                return response;
//            }
//        });

        OkHttpClient client = httpClient.build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    public DiscogsService provideDiscogsService(Retrofit retrofit) {
        return retrofit.create(DiscogsService.class);
    }

    @Provides
    public LoginPresenter provideLoginPresenter(DiscogsService service, Settings settings, RxJavaSchedulers schedulers) {
        return new LoginPresenter(service, settings, schedulers);
    }
}
