package work.beltran.discogsbrowser.app.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.api.LoginService;
import work.beltran.discogsbrowser.api.LoginServiceBuilder;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltran.work
 */
@Module
public class LoginModule {

    private LoginServiceBuilder builder;

    public LoginModule(LoginServiceBuilder builder) {
        this.builder = builder;
    }

    @Provides
    @Singleton
    public LoginService provideDiscogsService() {
        return builder.provideLoginService();
    }
}
