package work.beltran.discogsbrowser.api.di.modules;

import android.util.Log;

import java.io.IOException;
import java.util.Date;

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
import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.api.network.DiscogsService;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltran.work
 */
@Module
public class DiscogsModule {

    public static final String BASE_URL = "https://api.discogs.com/";
    private static final String CALLBACK = "discogs://callback";
    private String apiKey;
    private String consumerKey;
    private String consumerSecret;
    private String userToken;

    public DiscogsModule(String consumerKey, String consumerSecret, String userToken) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.userToken = userToken;
    }

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
//                        .header("Authorization", "Discogs token=" + apiKey)
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .header("Authorization",
                                "OAuth oauth_consumer_key=\"" + consumerKey + "\", " +
                                        "oauth_nonce=\"" + new Date().getTime() + "\", " +
                                        "oauth_token=\"" + userToken + "\", " +
                                        "oauth_signature=\"" + consumerSecret + "&\", " +
                                        "oauth_signature_method=\"PLAINTEXT\", " +
                                        "oauth_timestamp=\"" + new Date().getTime() + "\"")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });


        class LoggingInterceptor implements Interceptor {
            @Override public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                long t1 = System.nanoTime();
                Log.d("OkHttp", String.format("Sending request %s on %s%n%s",
                        request.url(), chain.connection(), request.headers()));

                Response response = chain.proceed(request);

                long t2 = System.nanoTime();
                Log.d("OkHttp", String.format("Received response for %s in %.1fms%n%s",
                        response.request().url(), (t2 - t1) / 1e6d, response.headers()));

                return response;
            }
        }

        OkHttpClient client = httpClient.addInterceptor(new LoggingInterceptor()).build();

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
