package work.beltran.discogsbrowser.business.login

import io.reactivex.Completable
import io.reactivex.Single
import work.beltran.discogsbrowser.api.LoginService
import work.beltran.discogsbrowser.business.LoginInteractor
import work.beltran.discogsbrowser.business.RxJavaSchedulers
import work.beltran.discogsbrowser.business.SettingsRepository
import work.beltran.discogsbrowser.business.Url

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
class LoginInteractorImpl(private val service: LoginService,
                          private val schedulers: RxJavaSchedulers,
                          private val settings: SettingsRepository) : LoginInteractor {

    override fun requestTokens(apiConsumerKey: String, apiConsumerSecret: String): Single<Url> {
        return service.requestToken(RequestHeader(apiConsumerKey, apiConsumerSecret).header)
                .map {
                    val url = OAUTH_PAGE + "?" + it.string()
                    val userToken = url.getQueryParameter("oauth_token")
                    val userSecret = url.getQueryParameter("oauth_token_secret")
                    settings.storeUserToken(userToken)
                    settings.storeUserSecret(userSecret)
                    return@map url
                }
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
    }

    override fun registerAccessToken(redirectUrl: Url, apiConsumerKey: String, apiConsumerSecret: String): Completable {
        if (redirectUrl.startsWith(REDIRECT_URI)) {
            val token = redirectUrl.getQueryParameter("oauth_token")
            val verifier = redirectUrl.getQueryParameter("oauth_verifier")
            return service.accessToken(AccessHeader(apiConsumerKey,
                            apiConsumerSecret + "&" + settings.getUserSecret(),
                            settings.getUserToken(),
                            verifier).header)
                    .doOnSuccess {
                        val url = OAUTH_PAGE + "?" + it.string()
                        val userToken = url.getQueryParameter("oauth_token")
                        val userSecret = url.getQueryParameter("oauth_token_secret")
                        settings.storeUserToken(userToken)
                        settings.storeUserSecret(userSecret)
                    }
                    .toCompletable()
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.mainThread())
        } else {
            return Completable.error(Throwable("Invalid URL: $redirectUrl"))
        }
    }

    companion object {
        private val OAUTH_PAGE = "https://discogs.com/oauth/authorize"
        private val REDIRECT_URI = "discogs://callback"
    }

    private fun Url.getQueryParameter(param: String): String {
        TODO()
    }
}

