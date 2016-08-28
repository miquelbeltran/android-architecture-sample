package work.beltran.discogsbrowser.app.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.business.settings.Settings;

/**
 * Created by Miquel Beltran on 02.05.16.
 * More on http://beltran.work
 */
@Module(includes = {ContextModule.class})
public class SettingsModule {

    @Provides
    @Singleton
    public Settings provideSettings(Context context) {
        return new Settings(context, BuildConfig.API_KEY);
    }
}
