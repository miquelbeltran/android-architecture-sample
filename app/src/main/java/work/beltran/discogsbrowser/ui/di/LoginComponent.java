package work.beltran.discogsbrowser.ui.di;

import javax.inject.Singleton;

import dagger.Component;
import work.beltran.discogsbrowser.di.modules.LoginPresenterModule;
import work.beltran.discogsbrowser.ui.di.modules.PicassoModule;
import work.beltran.discogsbrowser.ui.login.LoginActivity;

/**
 * Created by Miquel Beltran on 14.05.16.
 * More on http://beltran.work
 */
@Singleton
@Component(modules = {
        PicassoModule.class,
        LoginPresenterModule.class
})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
