package work.beltran.discogsbrowser.ui;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.ui.collection.CollectionActivity;
import work.beltran.discogsbrowser.ui.login.LoginActivity;
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
public class LauncherActivityTest {
    LauncherActivity activity;
    Settings settings;

    @Before
    public void setUp() throws Exception {
        settings = TestApp.mockSettings;
    }

    @Test
    public void testNoApiKey() throws Exception {
        when(settings.getApiKey()).thenReturn("");
        activity = Robolectric.setupActivity(LauncherActivity.class);
        verify(settings).getApiKey();
        Intent expected = new Intent(activity, LoginActivity.class);
        Intent actual = shadowOf(activity).getNextStartedActivity();
        assertThat(expected.getAction()).isEqualTo(actual.getAction());
    }

    @Test
    public void testApiKey() throws Exception {
        when(settings.getApiKey()).thenReturn("123456");
        activity = Robolectric.setupActivity(LauncherActivity.class);
        verify(settings).getApiKey();
        Intent expected = new Intent(activity, CollectionActivity.class);
        Intent actual = shadowOf(activity).getNextStartedActivity();
        assertThat(expected.getAction()).isEqualTo(actual.getAction());
    }
}
