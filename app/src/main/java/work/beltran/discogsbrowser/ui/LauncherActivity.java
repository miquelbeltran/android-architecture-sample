package work.beltran.discogsbrowser.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import work.beltran.discogsbrowser.ui.collection.CollectionActivity;

public class LauncherActivity extends Activity {

    private static final String TAG = LauncherActivity.class.getCanonicalName();
    private static final String PREFS_NAME = "DiscogsPreferences";
    private static final String API_KEY = "ApiKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String apiKey = settings.getString(API_KEY, "");


        Intent intent = new Intent(this, CollectionActivity.class);
        startActivity(intent);
        finish();
    }
}
