package work.beltran.discogsbrowser.ui.di.modules;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.api.ApiFrontend;
import work.beltran.discogsbrowser.api.di.modules.UserCollectionModule;
import work.beltran.discogsbrowser.ui.main.collection.CollectionRecordsAdapter;
import work.beltran.discogsbrowser.ui.main.search.SearchRecordsAdapter;
import work.beltran.discogsbrowser.ui.main.wantlist.WantRecordsAdapter;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
@Module(includes = {UserCollectionModule.class})
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
    public WantRecordsAdapter providesWantRecordsAdapter(ApiFrontend apiFrontend, Picasso picasso) {
        return new WantRecordsAdapter(apiFrontend.getWantedRecords());
    }
}
