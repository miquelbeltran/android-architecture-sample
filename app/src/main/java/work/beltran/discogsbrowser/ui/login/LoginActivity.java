package work.beltran.discogsbrowser.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.api.network.DiscogsService;
import work.beltran.discogsbrowser.ui.App;
import work.beltran.discogsbrowser.ui.settings.Settings;

public class LoginActivity extends AppCompatActivity {

    private static String OAUTH_PAGE = "https://discogs.com/oauth/authorize";

    @Inject
    public Settings settings;

    @Inject
    public Picasso picasso;

    @Inject
    public DiscogsService service;

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
                    service.requestToken()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
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
                                        Intent intent = new Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse(OAUTH_PAGE + "?" + responseBody.string()));
                                        startActivity(intent);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                }
            });
        }


    }
}
