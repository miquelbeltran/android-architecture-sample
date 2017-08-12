package work.beltran.discogsbrowser.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
class LoginServiceBuilder(private val applicationId: String) {

    fun provideLoginService(): LoginService {
        return provideRetrofit().create(LoginService::class.java)
    }

    private fun provideRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                    .header("User-Agent", applicationId)
                    .header("Content-Type", "application/x-www-form-urlencoded")
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    companion object {
        private const val BASE_URL = "https://api.discogs.com/"
    }
}
