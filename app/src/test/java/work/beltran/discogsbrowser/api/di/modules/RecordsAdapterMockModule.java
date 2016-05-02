package work.beltran.discogsbrowser.api.di.modules;

import work.beltran.discogsbrowser.api.UserCollection;
import work.beltran.discogsbrowser.ui.collection.RecordsAdapter;
import work.beltran.discogsbrowser.ui.di.modules.RecordsAdapterModule;
import com.squareup.picasso.Picasso;

import dagger.Module;

import static org.mockito.Mockito.mock;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltranfebrer.com
 */
@Module
public class RecordsAdapterMockModule extends RecordsAdapterModule {
    private RecordsAdapter mockAdapter;

    public RecordsAdapterMockModule(RecordsAdapter mockAdapter) {
        this.mockAdapter = mockAdapter;
    }

    @Override
    public RecordsAdapter providesRecordsAdapter(UserCollection userCollection, Picasso picasso) {
        return mockAdapter;
    }
}
