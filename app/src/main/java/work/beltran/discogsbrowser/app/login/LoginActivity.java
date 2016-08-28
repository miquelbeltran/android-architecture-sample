package work.beltran.discogsbrowser.app.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.app.App;
import work.beltran.discogsbrowser.app.LauncherActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {


    @Inject
    public Picasso picasso;

    @Inject
    public LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((App) getApplication()).getLoginComponent().inject(this);
        presenter.attachView(this);

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
    protected void onStart() {
        super.onStart();
        picasso.load(R.drawable.login_background).fit().centerCrop().into((ImageView) findViewById(R.id.loginBackground));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Uri uri = getIntent().getData();
        presenter.registerAccessToken(uri);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void startLauncher() {
        Intent intent = new Intent(this, LauncherActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void displayError(@StringRes int messageId) {

    }
}
