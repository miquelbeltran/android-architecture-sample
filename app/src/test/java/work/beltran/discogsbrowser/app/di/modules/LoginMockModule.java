package work.beltran.discogsbrowser.app.di.modules;


import work.beltran.discogsbrowser.api.LoginService;
import work.beltran.discogsbrowser.business.RxJavaSchedulers;
import work.beltran.discogsbrowser.app.login.LoginPresenter;
import work.beltran.discogsbrowser.app.settings.Settings;

import static org.mockito.Mockito.mock;

/**
 * Created by Miquel Beltran on 16.05.16.
 * More on http://beltran.work
 */
public class LoginMockModule extends LoginPresenterModule {
    @Override
    public LoginPresenter provideLoginPresenter(LoginService service, Settings settings, RxJavaSchedulers schedulers) {
        return mock(LoginPresenter.class);
    }
}
