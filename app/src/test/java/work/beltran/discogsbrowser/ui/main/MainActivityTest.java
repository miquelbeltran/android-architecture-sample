package work.beltran.discogsbrowser.ui.main;

import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.ui.TestApp;
import work.beltran.discogsbrowser.ui.settings.Settings;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, application = TestApp.class)
public class MainActivityTest {
    MainActivity activity;
    Settings settings;
    AHBottomNavigation navigation;

    @Before
    public void setUp() throws Exception {
        settings = TestApp.mockSettings;
        activity = Robolectric.setupActivity(MainActivity.class);
        navigation = (AHBottomNavigation) activity.findViewById(R.id.bottom_navigation);
    }

    @Test
    public void testSwitchFragments() throws Exception {
        navigation.setCurrentItem(0);
        View view = activity.findViewById(R.id.records_recycler_view);
        assertThat(view).isNotNull();
        assertThat(view.getVisibility()).isEqualTo(View.VISIBLE);
    }
}
