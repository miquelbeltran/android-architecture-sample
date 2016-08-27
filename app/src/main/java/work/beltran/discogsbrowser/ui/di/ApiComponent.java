package work.beltran.discogsbrowser.ui.di;

import javax.inject.Singleton;

import dagger.Component;
import work.beltran.discogsbrowser.di.modules.AveragePriceModule;
import work.beltran.discogsbrowser.ui.di.modules.CollectionModule;
import work.beltran.discogsbrowser.ui.di.modules.ErrorModule;
import work.beltran.discogsbrowser.ui.di.modules.PicassoModule;
import work.beltran.discogsbrowser.ui.di.modules.RecordsAdapterModule;
import work.beltran.discogsbrowser.ui.di.modules.SettingsModule;
import work.beltran.discogsbrowser.ui.collection.CollectionView;
import work.beltran.discogsbrowser.ui.main.search.SearchFragment;
import work.beltran.discogsbrowser.ui.main.search.SearchRecordsAdapterOld;
import work.beltran.discogsbrowser.ui.main.wantlist.WantRecordsAdapterOld;
import work.beltran.discogsbrowser.ui.main.wantlist.WantlistFragment;

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
        CollectionModule.class
})
public interface ApiComponent {
    void inject(WantlistFragment fragment);
    void inject(SearchFragment fragment);
    void inject(WantRecordsAdapterOld adapter);
    void inject(SearchRecordsAdapterOld adapter);
    void inject(CollectionView view);
}
