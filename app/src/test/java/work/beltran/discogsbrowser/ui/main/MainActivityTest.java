package work.beltran.discogsbrowser.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDialog;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.ui.TestApp;
import work.beltran.discogsbrowser.ui.login.LoginActivity;
import work.beltran.discogsbrowser.ui.settings.Settings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

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

    @Test
    public void testActivityWithBundle() throws Exception {
        Bundle bundle = new Bundle();
        bundle.putString("TAG", NavigationView.FragmentTag.Wantlist.name());
        activity = Robolectric.buildActivity(MainActivity.class).create().restoreInstanceState(bundle).resume().visible().get();
        View view = activity.findViewById(R.id.imageFav);
        assertThat(view).isNotNull();
        assertThat(view.getVisibility()).isEqualTo(View.VISIBLE);
    }

    @Test
    public void testSaveInstance() throws Exception {
        Bundle bundle = new Bundle();
        activity.onSaveInstanceState(bundle);
        assertThat(bundle.getString("TAG")).matches(NavigationView.FragmentTag.Collection.name());
    }

    @Test
    public void testErrorDialog() throws Exception {
        activity.showErrorInfo("test");
        AlertDialog dialog = (AlertDialog) ShadowDialog.getLatestDialog();
        Button button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        button.callOnClick();
        Intent expected = new Intent(activity, LoginActivity.class);
        Intent actual = shadowOf(activity).getNextStartedActivity();
        assertThat(expected.getAction()).isEqualTo(actual.getAction());
    }
}
