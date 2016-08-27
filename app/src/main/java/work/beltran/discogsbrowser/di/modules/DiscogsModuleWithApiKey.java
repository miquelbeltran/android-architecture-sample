package work.beltran.discogsbrowser.di.modules;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import work.beltran.discogsbrowser.BuildConfig;

/**
 * Created by Miquel Beltran on 16.05.16.
 * More on http://beltran.work
 */
public class DiscogsModuleWithApiKey extends DiscogsModule {
    private String apiKey;

    public DiscogsModuleWithApiKey(String apiKey) {
        super(null, null, null, null);
        this.apiKey = apiKey;
    }

    @Override
    public Retrofit provideRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("User-Agent", BuildConfig.APPLICATION_ID)
                        .header("Content-Type", "application/x-www-form-urlencoded")
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

}
