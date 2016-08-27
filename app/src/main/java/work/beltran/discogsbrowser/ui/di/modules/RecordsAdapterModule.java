package work.beltran.discogsbrowser.ui.di.modules;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.business.ApiFrontend;
import work.beltran.discogsbrowser.di.modules.ApiFrontendModule;
import work.beltran.discogsbrowser.ui.main.collection.CollectionRecordsAdapter;
import work.beltran.discogsbrowser.ui.main.search.SearchRecordsAdapter;
import work.beltran.discogsbrowser.ui.main.wantlist.WantRecordsAdapter;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
@Module(includes = {ApiFrontendModule.class})
public class RecordsAdapterModule {
    @Provides
    public CollectionRecordsAdapter providesCollectionRecordsAdapter(ApiFrontend apiFrontend) {
        return new CollectionRecordsAdapter(apiFrontend.getCollectionRecords());
    }

    @Provides
    public SearchRecordsAdapter providesSearchRecordsAdapter(ApiFrontend apiFrontend) {
        return new SearchRecordsAdapter(apiFrontend.getSearchSubject());
    }

    @Provides
    public WantRecordsAdapter providesWantRecordsAdapter(ApiFrontend apiFrontend) {
        return new WantRecordsAdapter(apiFrontend.getWantedRecords());
    }
}
