package work.beltran.discogsbrowser.ui.login;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.ui.App;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private static String REDIRECT_URI = "discogs://callback";


    @Inject
    public Picasso picasso;

//    @Inject
    public LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((App) getApplication()).getLoginComponent().inject(this);
        picasso.load(R.drawable.login_background).fit().centerCrop().into((ImageView) findViewById(R.id.loginBackground));

        Button loginButton = (Button) findViewById(R.id.loginbutton);
        if (loginButton != null) {
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.loginOnClick();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(REDIRECT_URI)) {
            final String token = uri.getQueryParameter("oauth_token");
            String verifier = uri.getQueryParameter("oauth_verifier");

            if (token != null && verifier != null) {
//                service.accessToken(
//                        new AccessHeader(
//                                BuildConfig.API_CONSUMER_KEY,
//                                BuildConfig.API_CONSUMER_SECRET + "&" + settings.getUserSecret(),
//                                settings.getUserToken(),
//                                verifier).getHeader())
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<ResponseBody>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                e.printStackTrace();
//
//                            }
//
//                            @Override
//                            public void onNext(ResponseBody responseBody) {
//                                settings.storeApiKey(token);
//                                Intent intent = new Intent(LoginActivity.this, LauncherActivity.class);
//                                startActivity(intent);
//                                finish();
//                            }
//                        });
                // get access token
                // we'll do that in a minute
            } else if (uri.getQueryParameter("error") != null) {
                // show an error message here
            }
        }
    }
}
