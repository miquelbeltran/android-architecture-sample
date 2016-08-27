package work.beltran.discogsbrowser.di.modules;

import dagger.Provides;

/**
 * Created by miquel on 8/27/16.
 */

public class LoginPresenterModule {

    @Provides
    public LoginPresenter provideLoginPresenter(DiscogsService service, Settings settings, RxJavaSchedulers schedulers) {
        return new LoginPresenter(service, settings, schedulers);
    }
}
