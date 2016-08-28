package work.beltran.discogsbrowser.app.settings;

import android.support.v7.app.ActionBar;
import android.support.v7.view.menu.ActionMenuItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.app.TestApp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Miquel Beltran on 13.05.16.
 * More on http://beltran.work
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, application = TestApp.class)
public class SettingsActivityTest {
    SettingsActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(SettingsActivity.class);
    }

    @Test
    public void testOnCreate() throws Exception {
        ActionBar actionBar = activity.getSupportActionBar();
        assertThat(actionBar.getTitle()).matches("Settings");
        assertThat(actionBar.getDisplayOptions() & ActionBar.DISPLAY_HOME_AS_UP).isEqualTo(ActionBar.DISPLAY_HOME_AS_UP);
    }

    @Test
    public void testOnOptionsItemSelected() throws Exception {
        activity.onOptionsItemSelected(new ActionMenuItem(null, 0, 0, 0, 0, null));
        ShadowActivity activityShadow = shadowOf(activity);
        assertThat(activityShadow.isFinishing()).isFalse();
        activity.onOptionsItemSelected(new ActionMenuItem(null, 0, android.R.id.home, 0, 0, null));
        activityShadow = shadowOf(activity);
        assertThat(activityShadow.isFinishing()).isTrue();
    }
}