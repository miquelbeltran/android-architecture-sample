package work.beltran.discogsbrowser.ui.di;

import javax.inject.Singleton;

import dagger.Component;
import work.beltran.discogsbrowser.business.di.AveragePriceModule;
import work.beltran.discogsbrowser.ui.di.modules.ErrorModule;
import work.beltran.discogsbrowser.ui.di.modules.PicassoModule;
import work.beltran.discogsbrowser.ui.di.modules.RecordsAdapterModule;
import work.beltran.discogsbrowser.ui.di.modules.SettingsModule;
import work.beltran.discogsbrowser.ui.main.collection.CollectionFragment;
import work.beltran.discogsbrowser.ui.main.collection.CollectionRecordsAdapter;
import work.beltran.discogsbrowser.ui.main.search.SearchFragment;
import work.beltran.discogsbrowser.ui.main.search.SearchRecordsAdapter;
import work.beltran.discogsbrowser.ui.main.wantlist.WantRecordsAdapter;
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
        AveragePriceModule.class
})
public interface ApiComponent {
    void inject(CollectionFragment fragment);
    void inject(WantlistFragment fragment);
    void inject(SearchFragment fragment);
    void inject(CollectionRecordsAdapter adapter);
    void inject(WantRecordsAdapter adapter);
    void inject(SearchRecordsAdapter adapter);
}
