package work.beltran.discogsbrowser.business

import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
typealias Url = String

interface LoginInteractor {
    fun requestTokens(apiConsumerKey: String, apiConsumerSecret: String): Single<Url>
    fun requisterAccessToken(redirectUrl: Url, apiConsumerKey: String, apiConsumerSecret: String): Completable
}
