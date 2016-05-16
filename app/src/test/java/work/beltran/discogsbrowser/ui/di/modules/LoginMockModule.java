package work.beltran.discogsbrowser.ui.di.modules;

import work.beltran.discogsbrowser.api.RxJavaSchedulers;
import work.beltran.discogsbrowser.api.di.modules.LoginModule;
import work.beltran.discogsbrowser.api.network.DiscogsService;
import work.beltran.discogsbrowser.ui.login.LoginPresenter;
import work.beltran.discogsbrowser.ui.settings.Settings;

import static org.mockito.Mockito.mock;

/**
 * Created by Miquel Beltran on 16.05.16.
 * More on http://beltran.work
 */
public class LoginMockModule extends LoginModule {
    @Override
    public LoginPresenter provideLoginPresenter(DiscogsService service, Settings settings, RxJavaSchedulers schedulers) {
        return mock(LoginPresenter.class);
    }
}
