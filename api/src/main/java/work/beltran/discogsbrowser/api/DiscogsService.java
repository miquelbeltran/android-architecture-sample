package work.beltran.discogsbrowser.api;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import work.beltran.discogsbrowser.api.model.SearchResults;
import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.api.model.UserWanted;

/**
 * Created by Miquel Beltran on 22.04.16
 * More on http://beltran.work
 */
public interface DiscogsService {
    // display https://api.discogs.com/users/mike513/collection/folders/0/releases
    @GET("users/{user}/collection/folders/0/releases")
    rx.Observable<UserCollection> listRecords(@Path("user") String user,
                                              @Query("page") int page);

    @GET("users/{username}/collection/releases/{release_id}")
    rx.Observable<UserCollection> getRecordInCollection(@Path("username") String user,
                                                        @Path("release_id") int releaseId);

    @POST("users/{username}/collection/folders/1/releases/{release_id}")
    rx.Observable<Void> addToCollection(@Path("username") String user,
                                        @Path("release_id") int releaseId);

    @DELETE("users/{username}/collection/folders/1/releases/{release_id}/instances/{instance_id}")
    rx.Observable<Void> removeFromCollection(@Path("username") String user,
                                             @Path("release_id") int releaseId,
                                             @Path("instance_id") int instanceId);

    @GET("users/{username}")
    rx.Observable<UserProfile> getUserProfile(@Path("username") String username);

    @GET("/users/{username}/wants")
    rx.Observable<UserWanted> getWantedList(@Path("username") String username,
                                            @Query("page") int page);

    @GET("database/search")
    rx.Observable<SearchResults> search(@Query("q") String query,
                                        @Query("type") String type,
                                        @Query("format") String format,
                                        @Query("barcode") String barcode);

    @GET("oauth/identity")
    rx.Observable<UserIdentity> getUserIdentity();
}
