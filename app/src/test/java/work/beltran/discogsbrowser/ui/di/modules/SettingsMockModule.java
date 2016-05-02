package work.beltran.discogsbrowser.ui.di.modules;

import android.content.Context;

import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 02.05.16.
 * More on http://beltran.work
 */
public class SettingsMockModule extends SettingsModule {
    private Settings mockSettings;

    public SettingsMockModule(Settings mockSettings) {
        super();
        this.mockSettings = mockSettings;
    }

    @Override
    public Settings provideSettings(Context context) {
        return mockSettings;
    }
}
