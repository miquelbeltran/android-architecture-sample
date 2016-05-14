package work.beltran.discogsbrowser.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Miquel Beltran on 02.05.16.
 * More on http://beltran.work
 */
public class Settings {
    private static final String PREFS_NAME = "DiscogsPreferences";
    private static final String API_KEY = "ApiKey";
    private static final String USER_TOKEN = "UserToken";
    private static final String USER_SECRET = "UserSecret";
    public static final String COLLECTION_PRICES = "collection_prices";
    public static final String COLLECTION_PRICES_TYPE = "collection_prices_type";
    public static final String WANTLIST_PRICES = "wantlist_prices";
    public static final String WANTLIST_PRICES_TYPE = "wantlist_prices_type";
    public static final String PRICE_TYPE_LOWEST = "0";
    private static Context context;
    private String apiKeyFromBuildConfig;

    public Settings(Context context, String apiKey) {
        this.context = context;
        apiKeyFromBuildConfig = apiKey;
    }

    public void storeApiKey(String accessToken) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        settings.edit().putString(API_KEY, accessToken).apply();
    }

    public String getApiKey() {
        String apiKey = apiKeyFromBuildConfig;
        if (!apiKey.isEmpty()) {
            return apiKey;
        }
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(API_KEY, "");
    }

    public SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void storeUserToken(String userToken) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        settings.edit().putString(USER_TOKEN, userToken).apply();
    }

    public void storeUserSecret(String userSecret) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        settings.edit().putString(USER_SECRET, userSecret).apply();
    }

    public String getUserToken() {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(USER_TOKEN, "");
    }

    public String getUserSecret() {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(USER_SECRET, "");
    }
}
