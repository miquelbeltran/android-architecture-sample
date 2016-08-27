package work.beltran.discogsbrowser.di.modules;

import dagger.Provides;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.business.RxJavaSchedulers;
import work.beltran.discogsbrowser.ui.login.LoginPresenter;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by miquel on 8/27/16.
 */

public class LoginPresenterModule {

    @Provides
    public LoginPresenter provideLoginPresenter(DiscogsService service, Settings settings, RxJavaSchedulers schedulers) {
        return new LoginPresenter(service, settings, schedulers);
    }
}
