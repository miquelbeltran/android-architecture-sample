package work.beltran.discogsbrowser.business.login;

import android.net.Uri;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;
import work.beltran.discogsbrowser.api.LoginService;
import work.beltran.discogsbrowser.business.LoginInteractor;
import work.beltran.discogsbrowser.business.RxJavaSchedulers;
import work.beltran.discogsbrowser.business.settings.Settings;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
public class LoginInteractorImpl implements LoginInteractor {

    private static String OAUTH_PAGE = "https://discogs.com/oauth/authorize";
    private static String REDIRECT_URI = "discogs://callback";
    private LoginService service;
    private RxJavaSchedulers schedulers;
    private Settings settings;

    public LoginInteractorImpl(LoginService service, RxJavaSchedulers schedulers, Settings settings) {
        this.service = service;
        this.schedulers = schedulers;
        this.settings = settings;
    }

    @Override
    public Observable<Uri> requestTokens(String apiConsumerKey, String apiConsumerSecret) {
        return service.requestToken(new RequestHeader(apiConsumerKey, apiConsumerSecret).getHeader())
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .flatMap(new Func1<ResponseBody, Observable<Uri>>() {
                    @Override
                    public Observable<Uri> call(ResponseBody responseBody) {
                        Uri uri = null;
                        try {
                            uri = Uri.parse(OAUTH_PAGE + "?" + responseBody.string());
                        } catch (IOException e) {
                            return Observable.error(e);
                        }
                        String userToken = uri.getQueryParameter("oauth_token");
                        String userSecret = uri.getQueryParameter("oauth_token_secret");
                        settings.storeUserToken(userToken);
                        settings.storeUserSecret(userSecret);
                        return Observable.just(uri);
                    }
                });
    }

    @Override
    public Observable<Boolean> requisterAccessToken(Uri uri, String apiConsumerKey, String apiConsumerSecret) {
        if (uri != null && uri.toString().startsWith(REDIRECT_URI)) {
            final String token = uri.getQueryParameter("oauth_token");
            String verifier = uri.getQueryParameter("oauth_verifier");
            if (token != null && verifier != null) {
                return service.accessToken(
                        new AccessHeader(
                                apiConsumerKey,
                                apiConsumerSecret + "&" + settings.getUserSecret(),
                                settings.getUserToken(),
                                verifier).getHeader())
                        .subscribeOn(schedulers.io())
                        .observeOn(schedulers.mainThread())
                        .flatMap(new Func1<ResponseBody, Observable<Boolean>>() {
                            @Override
                            public Observable<Boolean> call(ResponseBody responseBody) {
                                try {
                                    Uri uri = Uri.parse(OAUTH_PAGE + "?" + responseBody.string());
                                    String userToken = uri.getQueryParameter("oauth_token");
                                    String userSecret = uri.getQueryParameter("oauth_token_secret");
                                    settings.storeUserToken(userToken);
                                    settings.storeUserSecret(userSecret);
                                    return Observable.just(true);
                                } catch (IOException e) {
                                    return Observable.error(e);
                                }
                            }
                        });
            } else {
                return Observable.error(new Throwable("Missing Token"));
            }
        } else {
            return Observable.error(new Throwable("Invalid URI"));
        }
    }
}
