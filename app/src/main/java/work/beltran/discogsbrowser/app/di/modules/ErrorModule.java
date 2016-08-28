package work.beltran.discogsbrowser.app.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.app.errors.ErrorPresenter;
import work.beltran.discogsbrowser.app.settings.Settings;

/**
 * Created by Miquel Beltran on 03.05.16.
 * More on http://beltran.work
 */
@Module(includes = {SettingsModule.class})
public class ErrorModule {
    @Provides
    @Singleton
    ErrorPresenter provideErrorPresenter(Settings settings) {
        return new ErrorPresenter(settings);
    }
}
