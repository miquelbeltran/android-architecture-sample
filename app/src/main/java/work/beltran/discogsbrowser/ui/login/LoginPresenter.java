package work.beltran.discogsbrowser.ui.login;

import android.content.Intent;
import android.net.Uri;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observer;
import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.api.LoginService;
import work.beltran.discogsbrowser.business.RxJavaSchedulers;
import work.beltran.discogsbrowser.business.login.AccessHeader;
import work.beltran.discogsbrowser.business.login.RequestHeader;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 15.05.16.
 * More on http://beltran.work
 */
public class LoginPresenter {

    private static String OAUTH_PAGE = "https://discogs.com/oauth/authorize";
    private static String REDIRECT_URI = "discogs://callback";
    private LoginView view;
    private LoginService service;
    private Settings settings;
    private RxJavaSchedulers schedulers;

    public LoginPresenter(LoginService service, Settings settings, RxJavaSchedulers schedulers) {
        this.service = service;
        this.settings = settings;
        this.schedulers = schedulers;
    }

    public void loginOnClick() {
        service.requestToken(new RequestHeader(BuildConfig.API_CONSUMER_KEY, BuildConfig.API_CONSUMER_SECRET).getHeader())
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Uri uri = Uri.parse(OAUTH_PAGE + "?" + responseBody.string());
                            String userToken = uri.getQueryParameter("oauth_token");
                            String userSecret = uri.getQueryParameter("oauth_token_secret");
                            settings.storeUserToken(userToken);
                            settings.storeUserSecret(userSecret);
                            Intent intent = new Intent(
                                    Intent.ACTION_VIEW,
                                    uri);
                            view.startActivity(intent);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void setView(LoginView view) {
        this.view = view;
    }

    public void registerAccessToken(Uri uri) {
        if (uri != null && uri.toString().startsWith(REDIRECT_URI)) {
            final String token = uri.getQueryParameter("oauth_token");
            String verifier = uri.getQueryParameter("oauth_verifier");

            if (token != null && verifier != null) {
                service.accessToken(
                        new AccessHeader(
                                BuildConfig.API_CONSUMER_KEY,
                                BuildConfig.API_CONSUMER_SECRET + "&" + settings.getUserSecret(),
                                settings.getUserToken(),
                                verifier).getHeader())
                        .subscribeOn(schedulers.io())
                        .observeOn(schedulers.mainThread())
                        .subscribe(new Observer<ResponseBody>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                // TODO: remove stored keys for user!
                            }

                            @Override
                            public void onNext(ResponseBody responseBody) {
                                try {
                                    Uri uri = Uri.parse(OAUTH_PAGE + "?" + responseBody.string());
                                    String userToken = uri.getQueryParameter("oauth_token");
                                    String userSecret = uri.getQueryParameter("oauth_token_secret");
                                    settings.storeUserToken(userToken);
                                    settings.storeUserSecret(userSecret);
                                    // all good
                                    view.startLauncher();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                // get access token
                // we'll do that in a minute
            } else if (uri.getQueryParameter("error") != null) {
                // show an error message here
            }
        }
    }
}
