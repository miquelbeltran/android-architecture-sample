package work.beltran.discogsbrowser.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;

import work.beltran.discogsbrowser.BuildConfig;

/**
 * Created by Miquel Beltran on 02.05.16.
 * More on http://beltran.work
 */
public class Settings {
    private static final String PREFS_NAME = "DiscogsPreferences";
    private static final String API_KEY = "ApiKey";
    private static Context context;

    public Settings(Context context) {
        this.context = context;
    }

    public void storeApiKey(String accessToken) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        settings.edit().putString(API_KEY, accessToken).apply();
    }

    public String getApiKey() {
        String apiKey = BuildConfig.API_KEY;
        if (!apiKey.isEmpty()) {
            return apiKey;
        }
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(API_KEY, "");
    }
}
