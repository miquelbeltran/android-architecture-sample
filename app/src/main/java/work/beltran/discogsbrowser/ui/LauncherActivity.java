package work.beltran.discogsbrowser.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import work.beltran.discogsbrowser.ui.collection.CollectionActivity;

public class LauncherActivity extends Activity {

    private static final String TAG = LauncherActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        Intent intent = new Intent(this, CollectionActivity.class);
        startActivity(intent);
        finish();
    }
}
