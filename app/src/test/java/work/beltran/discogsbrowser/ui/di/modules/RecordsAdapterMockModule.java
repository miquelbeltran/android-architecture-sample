package work.beltran.discogsbrowser.ui.di.modules;

import work.beltran.discogsbrowser.api.ApiFrontend;
import work.beltran.discogsbrowser.ui.collection.RecordsAdapter;

import com.squareup.picasso.Picasso;

import dagger.Module;

import static org.mockito.Mockito.mock;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
@Module
public class RecordsAdapterMockModule extends RecordsAdapterModule {
    private RecordsAdapter mockAdapter;

    public RecordsAdapterMockModule(RecordsAdapter mockAdapter) {
        this.mockAdapter = mockAdapter;
    }

    @Override
    public RecordsAdapter providesRecordsAdapter(ApiFrontend apiFrontend, Picasso picasso) {
        return mockAdapter;
    }
}
