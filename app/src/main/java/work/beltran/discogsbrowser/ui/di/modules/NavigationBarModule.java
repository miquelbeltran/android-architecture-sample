package work.beltran.discogsbrowser.ui.di.modules;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.ui.main.NavigationAdapter;

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
