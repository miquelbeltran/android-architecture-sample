package work.beltran.discogsbrowser.ui.login;

import android.content.Intent;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.ui.LauncherActivity;
import work.beltran.discogsbrowser.ui.TestApp;
import work.beltran.discogsbrowser.ui.settings.Settings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Miquel Beltran on 02.05.16.
 * More on http://beltran.work
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, application = TestApp.class)
public class LoginActivityTest {
    LoginActivity activity;
    Settings settings;

    @Before
    public void setUp() throws Exception {
        settings = TestApp.mockSettings;
    }

    @Test
    public void testStoreKey() throws Exception {
        activity = Robolectric.setupActivity(LoginActivity.class);
        TextView textView = (TextView) activity.findViewById(R.id.accessToken);
        textView.setText("test");
        activity.onClickAccessToken(null);
        verify(settings).storeApiKey("test");
        Intent expected = new Intent(activity, LauncherActivity.class);
        Intent actual = shadowOf(activity).getNextStartedActivity();
        assertThat(expected.getAction()).isEqualTo(actual.getAction());
    }

}
