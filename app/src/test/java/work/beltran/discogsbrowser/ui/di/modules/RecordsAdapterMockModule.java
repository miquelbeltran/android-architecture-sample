package work.beltran.discogsbrowser.ui.di.modules;

import dagger.Module;
import work.beltran.discogsbrowser.api.ApiFrontend;
import work.beltran.discogsbrowser.ui.main.collection.CollectionRecordsAdapter;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
@Module
public class RecordsAdapterMockModule extends RecordsAdapterModule {
    private CollectionRecordsAdapter mockAdapter;

    public RecordsAdapterMockModule(CollectionRecordsAdapter mockAdapter) {
        this.mockAdapter = mockAdapter;
    }

    @Override
    public CollectionRecordsAdapter providesCollectionRecordsAdapter(ApiFrontend apiFrontend) {
        return mockAdapter;
    }
}
