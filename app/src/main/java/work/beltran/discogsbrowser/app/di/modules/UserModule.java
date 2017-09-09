package work.beltran.discogsbrowser.app.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    @Provides
    @Named("username")
    public String provideUsername() {
        // TODO
        return "mike513";
    }
}
