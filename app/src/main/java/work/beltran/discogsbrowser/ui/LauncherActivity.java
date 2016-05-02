package work.beltran.discogsbrowser.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.ui.collection.CollectionActivity;
import work.beltran.discogsbrowser.ui.login.LoginActivity;
import work.beltran.discogsbrowser.ui.settings.Settings;

public class LauncherActivity extends Activity {

    private static final String TAG = LauncherActivity.class.getCanonicalName();

    @Inject
    public Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        String apiKey = settings.getApiKey();
        if (apiKey.isEmpty()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            ((App) getApplication()).setApiKey(apiKey);
            Intent intent = new Intent(this, CollectionActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
