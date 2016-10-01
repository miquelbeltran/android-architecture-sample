package work.beltran.discogsbrowser.app.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import rx.Observer;
import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.app.base.BasePresenter;
import work.beltran.discogsbrowser.business.LoginInteractor;

/**
 * Created by Miquel Beltran on 15.05.16.
 * More on http://beltran.work
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginInteractor interactor;

    public LoginPresenter(LoginInteractor interactor) {
        this.interactor = interactor;
    }

    public void loginOnClick() {
        addSubscription(
                interactor
                        .requestTokens(
                                BuildConfig.API_CONSUMER_KEY,
                                BuildConfig.API_CONSUMER_SECRET)
                        .subscribe(new Observer<Uri>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(Uri uri) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                if (getView() != null)
                                getView().startActivity(intent);
                            }
                        }));
    }

    public void registerAccessToken(Uri uri) {
        addSubscription(
                interactor
                        .requisterAccessToken(
                                uri,
                                BuildConfig.API_CONSUMER_KEY,
                                BuildConfig.API_CONSUMER_SECRET)
                        .subscribe(new Observer<Boolean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(Boolean aBoolean) {
                                if (aBoolean && getView() != null) {
                                    getView().startLauncher();
                                }
                            }
                        }));
    }

    @Override
    public Bundle getStatus() {
        return null;
    }

    @Override
    public void loadStatus(Bundle bundle) {

    }
}
