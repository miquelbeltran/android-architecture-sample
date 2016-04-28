package com.beltranfebrer.discogsbrowser.ui.di.modules;

import com.beltranfebrer.discogsbrowser.api.UserCollection;
import com.beltranfebrer.discogsbrowser.api.di.modules.UserCollectionModule;
import com.beltranfebrer.discogsbrowser.ui.RecordsAdapter;
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
