package work.beltran.discogsbrowser.app.di.modules;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.app.common.RecordsAdapter;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
@Module(includes = {
        PicassoModule.class,
        ApiFrontendModule.class
})
public class RecordsAdapterModule {
    @Provides
    public RecordsAdapter provideAdapter(Picasso picasso) {
        return new RecordsAdapter(picasso);
    }
}
