package work.beltran.discogsbrowser.ui.di;

import work.beltran.discogsbrowser.ui.LauncherActivity;
import work.beltran.discogsbrowser.ui.collection.CollectionActivity;
import work.beltran.discogsbrowser.ui.di.modules.ContextModule;
import work.beltran.discogsbrowser.ui.di.modules.PicassoModule;
import work.beltran.discogsbrowser.ui.di.modules.RecordsAdapterModule;

import javax.inject.Singleton;

import dagger.Component;
import work.beltran.discogsbrowser.ui.di.modules.SettingsModule;
import work.beltran.discogsbrowser.ui.login.LoginActivity;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltranfebrer.com
 */
@Singleton
@Component(modules = {SettingsModule.class, PicassoModule.class})
public interface AppComponent {
    void inject(LoginActivity activity);
    void inject(LauncherActivity activity);
}
