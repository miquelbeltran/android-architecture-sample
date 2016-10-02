package work.beltran.discogsbrowser.app.release;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import work.beltran.discogsbrowser.R;

public class ReleaseActivity extends AppCompatActivity {

    private static final String EXTRA_RELEASE_ID = "EXTRA_RELEASE_ID";
    private static final String TAG = ReleaseActivity.class.getName();

    public static Intent createReleaseActivity(Context context, int releaseId) {
        Intent intent = new Intent(context, ReleaseActivity.class);
        intent.putExtra(EXTRA_RELEASE_ID, releaseId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        int releaseId = getIntent().getIntExtra(EXTRA_RELEASE_ID, 0);
        Log.d(TAG, "Release id: " + releaseId);
    }
}
