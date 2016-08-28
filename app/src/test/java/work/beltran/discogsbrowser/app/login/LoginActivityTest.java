package work.beltran.discogsbrowser.app.login;

import android.content.Intent;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.app.LauncherActivity;
import work.beltran.discogsbrowser.app.TestApp;
import work.beltran.discogsbrowser.app.settings.Settings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
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
        activity = Robolectric.setupActivity(LoginActivity.class);
    }

    @Test
    public void testStoreKey() throws Exception {
        assertThat(activity.picasso).isNotNull();
        verify(activity.presenter).setView(activity);
    }

    @Test
    public void testButtonClick() throws Exception {
        Button button = (Button) activity.findViewById(R.id.loginbutton);
        button.performClick();
        verify(activity.presenter).loginOnClick();
    }

    @Test
    public void testStartLauncher() throws Exception {
        activity.startLauncher();
        Intent expected = new Intent(activity, LauncherActivity.class);
        Intent actual = shadowOf(activity).getNextStartedActivity();
        assertThat(expected.getAction()).isEqualTo(actual.getAction());
    }
}
