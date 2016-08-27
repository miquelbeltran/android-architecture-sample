package work.beltran.discogsbrowser.ui.di.modules;

import dagger.Module;
import work.beltran.discogsbrowser.business.profile.ApiFrontend;
import work.beltran.discogsbrowser.ui.main.collection.CollectionRecordsAdapter;
import work.beltran.discogsbrowser.ui.main.wantlist.WantRecordsAdapter;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
@Module
public class RecordsAdapterMockModule extends RecordsAdapterModule {
    private CollectionRecordsAdapter mockAdapter;
    private WantRecordsAdapter mockWant;

    public RecordsAdapterMockModule(CollectionRecordsAdapter mockAdapter, WantRecordsAdapter mockWant) {
        this.mockAdapter = mockAdapter;
        this.mockWant = mockWant;
    }

    @Override
    public CollectionRecordsAdapter providesCollectionRecordsAdapter(ApiFrontend apiFrontend) {
        return mockAdapter;
    }

    @Override
    public WantRecordsAdapter providesWantRecordsAdapter(ApiFrontend apiFrontend) {
        return mockWant;
    }
}
