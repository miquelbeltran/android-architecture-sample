package work.beltran.discogsbrowser.api.di.modules;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.api.network.DiscogsService;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltran.work
 */
@Module
public class DiscogsModule {

    private String apiKey;

    public DiscogsModule(String apiKey) {
        this.apiKey = apiKey;
    }

    public static final String BASE_URL = "https://api.discogs.com/";

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("User-Agent", BuildConfig.APPLICATION_ID)
                        .header("Authorization", "Discogs token=" + apiKey)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    public DiscogsService provideDiscogsService(Retrofit retrofit) {
        return retrofit.create(DiscogsService.class);
    }
}
