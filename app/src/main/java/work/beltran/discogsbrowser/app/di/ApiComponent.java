package work.beltran.discogsbrowser.app.di;

import javax.inject.Singleton;

import dagger.Component;
import work.beltran.discogsbrowser.app.di.modules.AveragePriceModule;
import work.beltran.discogsbrowser.app.di.modules.CollectionModule;
import work.beltran.discogsbrowser.app.di.modules.ErrorModule;
import work.beltran.discogsbrowser.app.di.modules.PicassoModule;
import work.beltran.discogsbrowser.app.di.modules.RecordsAdapterModule;
import work.beltran.discogsbrowser.app.di.modules.SettingsModule;
import work.beltran.discogsbrowser.app.collection.CollectionFrameLayout;
import work.beltran.discogsbrowser.app.di.modules.WantlistModule;
import work.beltran.discogsbrowser.app.main.search.SearchFragment;
import work.beltran.discogsbrowser.app.main.search.SearchRecordsAdapterOld;
import work.beltran.discogsbrowser.app.wantlist.WantRecordsAdapterOld;
import work.beltran.discogsbrowser.app.wantlist.WantlistFragment;
import work.beltran.discogsbrowser.app.wantlist.WantlistFrameLayout;

/**
 * Created by Miquel Beltran on 02.05.16.
 * More on http://beltran.work
 */
@Singleton
@Component(modules = {
        SettingsModule.class,
        PicassoModule.class,
        ErrorModule.class,
        RecordsAdapterModule.class,
        AveragePriceModule.class,
        CollectionModule.class,
        WantlistModule.class
})
public interface ApiComponent {
    void inject(WantlistFragment fragment);
    void inject(SearchFragment fragment);
    void inject(WantRecordsAdapterOld adapter);
    void inject(SearchRecordsAdapterOld adapter);
    void inject(CollectionFrameLayout view);
    void inject(WantlistFrameLayout wantlistFrameLayout);
}
