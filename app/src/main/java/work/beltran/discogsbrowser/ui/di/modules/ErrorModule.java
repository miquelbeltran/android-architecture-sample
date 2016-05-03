package work.beltran.discogsbrowser.ui.di.modules;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.ui.errors.ErrorPresenter;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 03.05.16.
 * More on http://beltran.work
 */
@Module(includes = {SettingsModule.class})
public class ErrorModule {
    @Provides
    ErrorPresenter provideErrorPresenter(Settings settings) {
        return new ErrorPresenter(settings);
    }
}
