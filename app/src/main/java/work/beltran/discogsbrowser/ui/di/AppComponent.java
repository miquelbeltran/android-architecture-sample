package work.beltran.discogsbrowser.ui.di;

import javax.inject.Singleton;

import dagger.Component;
import work.beltran.discogsbrowser.ui.LauncherActivity;
import work.beltran.discogsbrowser.ui.di.modules.ErrorModule;
import work.beltran.discogsbrowser.ui.di.modules.NavigationBarModule;
import work.beltran.discogsbrowser.ui.di.modules.PicassoModule;
import work.beltran.discogsbrowser.ui.di.modules.SettingsModule;
import work.beltran.discogsbrowser.ui.main.MainActivity;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltran.work
 */
@Singleton
@Component(modules = {
        SettingsModule.class,
        PicassoModule.class,
        ErrorModule.class,
        NavigationBarModule.class
})
public interface AppComponent {

    void inject(LauncherActivity activity);

    void inject(MainActivity activity);

}
