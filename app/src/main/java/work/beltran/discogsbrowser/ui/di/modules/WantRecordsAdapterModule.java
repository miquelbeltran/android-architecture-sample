package work.beltran.discogsbrowser.ui.di.modules;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.api.ApiFrontend;
import work.beltran.discogsbrowser.api.di.modules.UserCollectionModule;
import work.beltran.discogsbrowser.ui.collection.RecordsAdapter;
import work.beltran.discogsbrowser.ui.wantlist.WantRecordsAdapter;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
@Module(includes = {UserCollectionModule.class, PicassoModule.class})
public class WantRecordsAdapterModule {
    @Provides
    public WantRecordsAdapter providesRecordsAdapter(ApiFrontend apiFrontend, Picasso picasso) {
        return new WantRecordsAdapter(apiFrontend, picasso);
    }
}
