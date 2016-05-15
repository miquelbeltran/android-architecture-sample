package work.beltran.discogsbrowser.ui.login;

import android.content.Intent;
import android.net.Uri;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observer;
import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.api.network.DiscogsService;
import work.beltran.discogsbrowser.api.network.login.RequestHeader;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 15.05.16.
 * More on http://beltran.work
 */
public class LoginPresenter {

    private static String OAUTH_PAGE = "https://discogs.com/oauth/authorize";
    private LoginView view;

    @Inject
    public DiscogsService service;

    @Inject
    public Settings settings;


    public void loginOnClick() {
        service.requestToken(new RequestHeader(BuildConfig.API_CONSUMER_KEY, BuildConfig.API_CONSUMER_SECRET).getHeader())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
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
}
