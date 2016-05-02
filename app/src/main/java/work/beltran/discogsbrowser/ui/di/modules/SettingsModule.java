package work.beltran.discogsbrowser.ui.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.ui.di.modules.ContextModule;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 02.05.16.
 * More on http://beltranfebrer.com
 */
@Module(includes = {ContextModule.class})
public class SettingsModule {

    @Provides
    @Singleton
    public Settings provideSettings(Context context) {
        return new Settings(context);
    }
}
