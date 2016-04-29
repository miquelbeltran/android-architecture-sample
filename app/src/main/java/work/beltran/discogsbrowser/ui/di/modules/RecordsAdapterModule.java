package work.beltran.discogsbrowser.ui.di.modules;

import work.beltran.discogsbrowser.api.UserCollection;
import work.beltran.discogsbrowser.api.di.modules.UserCollectionModule;
import work.beltran.discogsbrowser.ui.collection.RecordsAdapter;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltranfebrer.com
 */
@Module(includes = {UserCollectionModule.class, PicassoModule.class})
public class RecordsAdapterModule {
    @Provides
    public RecordsAdapter providesRecordsAdapter(UserCollection userCollection, Picasso picasso) {
        return new RecordsAdapter(userCollection, picasso);
    }
}
