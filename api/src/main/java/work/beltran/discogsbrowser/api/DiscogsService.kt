package work.beltran.discogsbrowser.api

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import work.beltran.discogsbrowser.api.model.SearchResults
import work.beltran.discogsbrowser.api.model.UserCollection
import work.beltran.discogsbrowser.api.model.UserIdentity
import work.beltran.discogsbrowser.api.model.UserProfile
import work.beltran.discogsbrowser.api.model.UserWanted

/**
 * Created by Miquel Beltran on 22.04.16
 * More on http://beltran.work
 */
interface DiscogsService {
    // display https://api.discogs.com/users/mike513/collection/folders/0/releases
    @GET("users/{user}/collection/folders/0/releases")
    fun listRecords(@Path("user") user: String,
                    @Query("page") page: Int): Single<UserCollection>

    @GET("users/{username}/collection/releases/{release_id}")
    fun getRecordInCollection(@Path("username") user: String,
                              @Path("release_id") releaseId: Int): Single<UserCollection>

    @POST("users/{username}/collection/folders/1/releases/{release_id}")
    fun addToCollection(@Path("username") user: String,
                        @Path("release_id") releaseId: Int): Completable

    @DELETE("users/{username}/collection/folders/1/releases/{release_id}/instances/{instance_id}")
    fun removeFromCollection(@Path("username") user: String,
                             @Path("release_id") releaseId: Int,
                             @Path("instance_id") instanceId: Int): Completable

    @GET("users/{username}")
    fun getUserProfile(@Path("username") username: String): Single<UserProfile>

    @GET("/users/{username}/wants")
    fun getWantedList(@Path("username") username: String,
                      @Query("page") page: Int): Single<UserWanted>

    @GET("database/search")
    fun search(@Query("q") query: String,
               @Query("type") type: String,
               @Query("format") format: String,
               @Query("barcode") barcode: String): Single<SearchResults>

    @get:GET("oauth/identity")
    val userIdentity: Single<UserIdentity>
}
