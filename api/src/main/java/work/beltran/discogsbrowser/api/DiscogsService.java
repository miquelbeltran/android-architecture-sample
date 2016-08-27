package work.beltran.discogsbrowser.api;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import work.beltran.discogsbrowser.api.model.MarketResult;
import work.beltran.discogsbrowser.api.model.SearchResults;
import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.api.model.UserWanted;

/**
 * Created by miquel on 22.04.16.
 */
public interface DiscogsService {
    // display https://api.discogs.com/users/mike513/collection/folders/0/releases
    @GET("users/{user}/collection/folders/0/releases")
    rx.Observable<UserCollection> listRecords(@Path("user") String user, @Query("page") int page);

    @GET("oauth/identity")
    rx.Observable<UserIdentity> getUserIdentity();

    @GET("users/{username}")
    rx.Observable<UserProfile> getUserProfile(@Path("username") String username);

    @GET("/users/{username}/wants")
    rx.Observable<UserWanted> getWantedList(@Path("username") String username, @Query("page") int page);

    @GET("marketplace/search")
    rx.Observable<List<MarketResult>> getMarketResults(@Query("release_id") int releaseId);

    @GET("database/search")
    rx.Observable<SearchResults> search(@Query("q") String query,
                                        @Query("type") String type,
                                        @Query("format") String format,
                                        @Query("barcode") String barcode);

    @GET("oauth/request_token")
    rx.Observable<ResponseBody> requestToken(@Header("Authorization") String authHeader);

    @POST("oauth/access_token")
    rx.Observable<ResponseBody> accessToken(@Header("Authorization") String authHeader);
}
