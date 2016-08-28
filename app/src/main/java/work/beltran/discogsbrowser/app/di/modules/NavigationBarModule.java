package work.beltran.discogsbrowser.app.di.modules;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.app.main.NavigationAdapter;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
@Module
public class NavigationBarModule {
    @Provides
    public NavigationAdapter providesNavigationBarPresenter() {
         return new NavigationAdapter();
    }
}
