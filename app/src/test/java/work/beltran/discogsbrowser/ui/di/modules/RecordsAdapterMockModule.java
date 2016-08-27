package work.beltran.discogsbrowser.ui.di.modules;

import dagger.Module;
import work.beltran.discogsbrowser.business.old.ApiFrontend;
import work.beltran.discogsbrowser.ui.main.collection.CollectionRecordsAdapterOld;
import work.beltran.discogsbrowser.ui.main.wantlist.WantRecordsAdapterOld;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
@Module
public class RecordsAdapterMockModule extends RecordsAdapterModule {
    private CollectionRecordsAdapterOld mockAdapter;
    private WantRecordsAdapterOld mockWant;

    public RecordsAdapterMockModule(CollectionRecordsAdapterOld mockAdapter, WantRecordsAdapterOld mockWant) {
        this.mockAdapter = mockAdapter;
        this.mockWant = mockWant;
    }

    @Override
    public CollectionRecordsAdapterOld providesCollectionRecordsAdapter(ApiFrontend apiFrontend) {
        return mockAdapter;
    }

    @Override
    public WantRecordsAdapterOld providesWantRecordsAdapter(ApiFrontend apiFrontend) {
        return mockWant;
    }
}
