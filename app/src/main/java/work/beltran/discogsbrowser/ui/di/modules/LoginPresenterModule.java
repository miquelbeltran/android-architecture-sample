package work.beltran.discogsbrowser.ui.di.modules;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.api.LoginService;
import work.beltran.discogsbrowser.business.RxJavaSchedulers;
import work.beltran.discogsbrowser.ui.login.LoginPresenter;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by miquel on 8/27/16.
 */
@Module(includes = {
        LoginModule.class,
        SettingsModule.class,
        RxJavaSchedulersModule.class
})
public class LoginPresenterModule {

    @Provides
    public LoginPresenter provideLoginPresenter(LoginService service, Settings settings, RxJavaSchedulers schedulers) {
        return new LoginPresenter(service, settings, schedulers);
    }
}
