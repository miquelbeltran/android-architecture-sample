package work.beltran.discogsbrowser.ui.di;

import javax.inject.Singleton;

import dagger.Component;
import work.beltran.discogsbrowser.api.di.modules.UserCollectionModule;
import work.beltran.discogsbrowser.ui.collection.CollectionActivity;
import work.beltran.discogsbrowser.ui.di.modules.RecordsAdapterModule;

/**
 * Created by Miquel Beltran on 02.05.16.
 * More on http://beltranfebrer.com
 */
@Singleton
@Component(modules = {RecordsAdapterModule.class})
public interface ApiComponent {
    void inject(CollectionActivity activity);
}
