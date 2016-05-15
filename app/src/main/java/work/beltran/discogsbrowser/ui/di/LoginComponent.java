package work.beltran.discogsbrowser.ui.di;

import javax.inject.Singleton;

import dagger.Component;
import work.beltran.discogsbrowser.api.di.modules.LoginModule;
import work.beltran.discogsbrowser.ui.di.modules.PicassoModule;
import work.beltran.discogsbrowser.ui.di.modules.RxJavaSchedulersModule;
import work.beltran.discogsbrowser.ui.di.modules.SettingsModule;
import work.beltran.discogsbrowser.ui.login.LoginActivity;
import work.beltran.discogsbrowser.ui.login.LoginPresenter;

/**
 * Created by Miquel Beltran on 14.05.16.
 * More on http://beltran.work
 */
@Singleton
@Component(modules = {
        SettingsModule.class,
        PicassoModule.class,
        LoginModule.class,
        RxJavaSchedulersModule.class
})
public interface LoginComponent {
    void inject(LoginActivity activity);

    void inject(LoginPresenter presenter);
}
