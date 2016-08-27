package work.beltran.discogsbrowser.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import work.beltran.discogsbrowser.api.model.UserIdentity;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public interface LoginService {
    @GET("oauth/identity")
    rx.Observable<UserIdentity> getUserIdentity();

    @GET("oauth/request_token")
    rx.Observable<ResponseBody> requestToken(@Header("Authorization") String authHeader);

    @POST("oauth/access_token")
    rx.Observable<ResponseBody> accessToken(@Header("Authorization") String authHeader);
}
