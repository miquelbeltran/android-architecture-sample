package work.beltran.discogsbrowser.ui.di.modules;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.business.old.ApiFrontend;
import work.beltran.discogsbrowser.di.modules.ApiFrontendModule;
import work.beltran.discogsbrowser.ui.collection.CollectionRecordsAdapterOld;
import work.beltran.discogsbrowser.ui.common.RecordsAdapter;
import work.beltran.discogsbrowser.ui.main.search.SearchRecordsAdapterOld;
import work.beltran.discogsbrowser.ui.main.wantlist.WantRecordsAdapterOld;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
@Module(includes = {ApiFrontendModule.class})
public class RecordsAdapterModule {
    @Provides
    public RecordsAdapter provideAdapter() {
        return new RecordsAdapter();
    }

    @Provides
    public CollectionRecordsAdapterOld providesCollectionRecordsAdapter(ApiFrontend apiFrontend) {
        return new CollectionRecordsAdapterOld(apiFrontend.getCollectionRecords());
    }

    @Provides
    public SearchRecordsAdapterOld providesSearchRecordsAdapter(ApiFrontend apiFrontend) {
        return new SearchRecordsAdapterOld(apiFrontend.getSearchSubject());
    }

    @Provides
    public WantRecordsAdapterOld providesWantRecordsAdapter(ApiFrontend apiFrontend) {
        return new WantRecordsAdapterOld(apiFrontend.getWantedRecords());
    }
}
