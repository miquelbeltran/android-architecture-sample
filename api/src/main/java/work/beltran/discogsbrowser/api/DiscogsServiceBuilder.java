package work.beltran.discogsbrowser.api;

import java.io.IOException;
import java.util.Date;

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
public class DiscogsServiceBuilder {

    public static final String BASE_URL = "https://api.discogs.com/";
    private static final String CALLBACK = "discogs://callback";
    private String consumerKey;
    private String consumerSecret;
    private String userToken;
    private String userSecret;
    private String applicationId;

    public DiscogsServiceBuilder(String consumerKey,
                                 String consumerSecret,
                                 String userToken,
                                 String userSecret,
                                 String applicationId) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.userToken = userToken;
        this.userSecret = userSecret;
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
                        .header("Authorization",
                                "OAuth oauth_consumer_key=\"" + consumerKey + "\", " +
                                        "oauth_nonce=\"" + new Date().getTime() + "\", " +
                                        "oauth_token=\"" + userToken + "\", " +
                                        "oauth_signature=\"" + consumerSecret + "&" + userSecret + "\", " +
                                        "oauth_signature_method=\"PLAINTEXT\", " +
                                        "oauth_timestamp=\"" + new Date().getTime() + "\"")
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
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

}
