package work.beltran.discogsbrowser.ui.di;

import javax.inject.Singleton;

import dagger.Component;
import work.beltran.discogsbrowser.api.UserCollection;
import work.beltran.discogsbrowser.ui.collection.CollectionFragment;
import work.beltran.discogsbrowser.ui.di.modules.RecordsAdapterModule;

/**
 * Created by Miquel Beltran on 02.05.16.
 * More on http://beltran.work
 */
@Singleton
@Component(modules = {RecordsAdapterModule.class })
public interface ApiComponent {
    void inject(CollectionFragment fragment);
    UserCollection userCollection();
}
