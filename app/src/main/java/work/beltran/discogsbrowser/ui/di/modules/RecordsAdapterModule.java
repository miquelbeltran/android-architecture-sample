package work.beltran.discogsbrowser.ui.di.modules;

import work.beltran.discogsbrowser.api.ApiFrontend;
import work.beltran.discogsbrowser.api.di.modules.UserCollectionModule;
import work.beltran.discogsbrowser.ui.collection.RecordsAdapter;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
@Module(includes = {UserCollectionModule.class, PicassoModule.class})
public class RecordsAdapterModule {
    @Provides
    public RecordsAdapter providesRecordsAdapter(ApiFrontend apiFrontend, Picasso picasso) {
        return new RecordsAdapter(apiFrontend, picasso);
    }
}
