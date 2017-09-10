package work.beltran.discogsbrowser.app.di;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Component;
import work.beltran.discogsbrowser.app.collection.CollectionController;
import work.beltran.discogsbrowser.app.collection.CollectionViewModel;
import work.beltran.discogsbrowser.app.di.modules.CollectionModule;
import work.beltran.discogsbrowser.app.di.modules.PicassoModule;
import work.beltran.discogsbrowser.app.di.modules.SearchModule;
import work.beltran.discogsbrowser.app.di.modules.SettingsModule;
import work.beltran.discogsbrowser.app.di.modules.WantlistModule;
import work.beltran.discogsbrowser.app.release.ReleaseActivity;
import work.beltran.discogsbrowser.app.search.SearchFrameLayout;
import work.beltran.discogsbrowser.app.wantlist.WantlistFrameLayout;

/**
 * Created by Miquel Beltran on 02.05.16.
 * More on http://beltran.work
 */
@Singleton
@Component(modules = {
        SettingsModule.class,
        PicassoModule.class,
        CollectionModule.class,
        WantlistModule.class,
        SearchModule.class
})
public interface ApiComponent {
    void inject(WantlistFrameLayout wantlistFrameLayout);
    void inject(SearchFrameLayout searchFrameLayout);
    void inject(ReleaseActivity releaseActivity);
    void inject(CollectionController collectionController);
    CollectionViewModel collectionViewModel();
}
