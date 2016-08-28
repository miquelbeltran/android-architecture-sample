package work.beltran.discogsbrowser.app.di;

import javax.inject.Singleton;

import dagger.Component;
import work.beltran.discogsbrowser.app.collection.CollectionFrameLayout;
import work.beltran.discogsbrowser.app.di.modules.CollectionModule;
import work.beltran.discogsbrowser.app.di.modules.PicassoModule;
import work.beltran.discogsbrowser.app.di.modules.RecordsAdapterModule;
import work.beltran.discogsbrowser.app.di.modules.SearchModule;
import work.beltran.discogsbrowser.app.di.modules.SettingsModule;
import work.beltran.discogsbrowser.app.di.modules.WantlistModule;
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
        RecordsAdapterModule.class,
        CollectionModule.class,
        WantlistModule.class,
        SearchModule.class
})
public interface ApiComponent {
    void inject(CollectionFrameLayout view);
    void inject(WantlistFrameLayout wantlistFrameLayout);
    void inject(SearchFrameLayout searchFrameLayout);
}
