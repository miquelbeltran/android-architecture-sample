package work.beltran.discogsbrowser.app.di;

import javax.inject.Singleton;

import dagger.Component;
import work.beltran.discogsbrowser.app.LauncherActivity;
import work.beltran.discogsbrowser.app.di.modules.NavigationBarModule;
import work.beltran.discogsbrowser.app.di.modules.PicassoModule;
import work.beltran.discogsbrowser.app.di.modules.SettingsModule;
import work.beltran.discogsbrowser.app.main.MainActivity;
import work.beltran.discogsbrowser.app.settings.Settings;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltran.work
 */
@Singleton
@Component(modules = {
        SettingsModule.class,
        PicassoModule.class,
        NavigationBarModule.class
})
public interface AppComponent {

    void inject(LauncherActivity activity);

    void inject(MainActivity activity);

    Settings getSettings();
}
