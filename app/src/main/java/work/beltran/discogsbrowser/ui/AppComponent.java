package work.beltran.discogsbrowser.ui;

import work.beltran.discogsbrowser.ui.collection.CollectionActivity;
import work.beltran.discogsbrowser.ui.collection.modules.RecordsAdapterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltranfebrer.com
 */
@Singleton
@Component(modules = {RecordsAdapterModule.class})
public interface AppComponent {
    void inject(CollectionActivity activity);
}
