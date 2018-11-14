package work.beltran.discogsbrowser.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DiscogsService {

    @GET("users/{username}/collection/folders/{folder_id}/releases")
    fun getCollectionItemsByFolder(
        @Path("username") username: String,
        @Path("folder_id") folderId: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : Deferred<Response<CollectionItemsByFolderResponse>>
}

fun provideService(): DiscogsService {

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    return Retrofit
        .Builder()
        .baseUrl("https://api.discogs.com")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(DiscogsService::class.java)
}
