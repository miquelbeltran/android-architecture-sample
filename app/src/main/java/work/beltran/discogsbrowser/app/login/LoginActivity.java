package work.beltran.discogsbrowser.app.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.app.App;
import work.beltran.discogsbrowser.app.LauncherActivity;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.loginBackground)
    ImageView imageBackground;

    @Inject
    public Picasso picasso;

//    @Inject
//    public LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ((App) getApplication()).getLoginComponent().inject(this);
//        presenter.attachView(this);
        picasso.load(R.drawable.login_background)
                .fit()
                .centerCrop()
                .into(imageBackground);
    }

//    @OnClick(R.id.loginbutton)
//    public void onClick() {
//        presenter.loginOnClick();
//    }

    @Override
    protected void onResume() {
        super.onResume();
        Uri uri = getIntent().getData();
//        presenter.registerAccessToken(uri);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        presenter.detachView();
    }

    public void startLauncher() {
        Intent intent = new Intent(this, LauncherActivity.class);
        startActivity(intent);
        finish();
    }

//    @Override
//    public void displayError(@StringRes int messageId) {
//
//    }
}
