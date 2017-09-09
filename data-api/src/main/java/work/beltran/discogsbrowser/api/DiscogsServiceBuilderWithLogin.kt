package work.beltran.discogsbrowser.api

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
class DiscogsServiceBuilderWithLogin(private val consumerKey: String,
                                     private val consumerSecret: String,
                                     private val userToken: String,
                                     private val userSecret: String,
                                     private val applicationId: String): DiscogsServiceBuilder {

    override fun provideDiscogsService(moshi: Moshi): DiscogsService {
        return provideRetrofit(moshi).create(DiscogsService::class.java)
    }

    private fun provideRetrofit(moshi: Moshi): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                    .header("User-Agent", applicationId)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Authorization",
                            "OAuth oauth_consumer_key=\"" + consumerKey + "\", " +
                                    "oauth_nonce=\"" + Date().time + "\", " +
                                    "oauth_token=\"" + userToken + "\", " +
                                    "oauth_signature=\"" + consumerSecret + "&" + userSecret + "\", " +
                                    "oauth_signature_method=\"PLAINTEXT\", " +
                                    "oauth_timestamp=\"" + Date().time + "\"")
                    .method(original.method(), original.body())
                    .build()
            chain.proceed(request)
        }


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

        val client = httpClient.build()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    companion object {
        private const val BASE_URL = "https://api.discogs.com/"
        private val CALLBACK = "discogs://callback"
    }
}

