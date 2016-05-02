package work.beltran.discogsbrowser.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.ui.collection.CollectionActivity;
import work.beltran.discogsbrowser.ui.login.LoginActivity;

public class LauncherActivity extends Activity {

    private static final String TAG = LauncherActivity.class.getCanonicalName();
    private static final String PREFS_NAME = "DiscogsPreferences";
    private static final String API_KEY = "ApiKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");

        String apiKey = BuildConfig.API_KEY;
        if (apiKey.isEmpty()) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            apiKey = settings.getString(API_KEY, "");
        }
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
