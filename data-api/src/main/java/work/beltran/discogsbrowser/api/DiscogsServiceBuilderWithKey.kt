package work.beltran.discogsbrowser.api

import com.squareup.moshi.Moshi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
class DiscogsServiceBuilderWithKey(private val apiKey: String,
                                   private val applicationId: String) {


    fun provideDiscogsService(moshi: Moshi): DiscogsService {
        return provideRetrofit(moshi).create(DiscogsService::class.java)
    }

    private fun provideRetrofit(moshi: Moshi): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                    .header("User-Agent", applicationId)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Authorization", "Discogs token=" + apiKey)
                    .method(original.method(), original.body())
                    .build()
            chain.proceed(request)
        }

        val client = httpClient.build()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    companion object {
        val BASE_URL = "https://api.discogs.com/"
    }
}
