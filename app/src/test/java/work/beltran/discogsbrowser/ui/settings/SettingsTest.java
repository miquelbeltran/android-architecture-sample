package work.beltran.discogsbrowser.ui.settings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
@RunWith(RobolectricTestRunner.class)
public class SettingsTest {
    private static final String API_KEY_TEST = "API_KEY_TEST";
    private static final String API_KEY_BUILD = "API_KEY_BUILD";
    Settings settings;

    @Test
    public void testStoreApiKey() throws Exception {
        settings = new Settings(RuntimeEnvironment.application, "");
        assertThat(settings.getApiKey()).matches("");
        settings.storeApiKey(API_KEY_TEST);
        assertThat(settings.getApiKey()).matches(API_KEY_TEST);
    }

    @Test
    public void testApiKeyFromBuildCondig() throws Exception {
        settings = new Settings(RuntimeEnvironment.application, API_KEY_BUILD);
        assertThat(settings.getApiKey()).matches(API_KEY_BUILD);
        settings.storeApiKey(API_KEY_TEST);
        assertThat(settings.getApiKey()).matches(API_KEY_BUILD);
    }

    @Test
    public void testGetDefaultSettings() throws Exception {
        settings = new Settings(RuntimeEnvironment.application, "");
        assertThat(settings.getSharedPreferences()).isNotNull();
    }
}
