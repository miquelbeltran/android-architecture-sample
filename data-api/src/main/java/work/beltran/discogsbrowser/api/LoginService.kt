package work.beltran.discogsbrowser.api

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
interface LoginService {

    @GET("oauth/request_token")
    fun requestToken(@Header("Authorization") authHeader: String): Single<ResponseBody>

    @POST("oauth/access_token")
    fun accessToken(@Header("Authorization") authHeader: String): Single<ResponseBody>
}
