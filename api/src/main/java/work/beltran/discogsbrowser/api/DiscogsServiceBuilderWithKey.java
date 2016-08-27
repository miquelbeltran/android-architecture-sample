package work.beltran.discogsbrowser.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class DiscogsServiceBuilderWithKey  {
    public static final String BASE_URL = "https://api.discogs.com/";

    private final String apiKey;
    private final String applicationId;

    public DiscogsServiceBuilderWithKey(String apiKey,
                                        String applicationId) {
        this.apiKey = apiKey;
        this.applicationId = applicationId;
    }


    public DiscogsService provideDiscogsService() {
        return provideRetrofit().create(DiscogsService.class);
    }

    private Retrofit provideRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("User-Agent", applicationId)
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
