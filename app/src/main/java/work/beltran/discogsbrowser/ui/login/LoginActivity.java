package work.beltran.discogsbrowser.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.ui.App;
import work.beltran.discogsbrowser.ui.LauncherActivity;
import work.beltran.discogsbrowser.ui.settings.Settings;

public class LoginActivity extends AppCompatActivity {

    @Inject
    public Settings settings;

    @Inject
    public Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((App) getApplication()).getAppComponent().inject(this);
        picasso.load(R.drawable.login_background).fit().centerCrop().into((ImageView) findViewById(R.id.loginBackground));
    }

    public void onClickAccessToken(View view) {
        TextView textView = (TextView) findViewById(R.id.accessToken);
        String accessToken = textView.getText().toString();
        settings.storeApiKey(accessToken);
        Intent intent = new Intent(this, LauncherActivity.class);
        startActivity(intent);
        finish();
    }
}
