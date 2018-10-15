package work.beltran.discogsbrowser.api

import retrofit2.Call
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
    ) : Call<CollectionItemsByFolderResponse>
}

fun provideService(): DiscogsService {
    return Retrofit
        .Builder()
        .baseUrl("https://api.discogs.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(DiscogsService::class.java)
}
