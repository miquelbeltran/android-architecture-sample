package com.beltranfebrer.discogsbrowser.api.di.modules;

import com.beltranfebrer.discogsbrowser.api.DiscogsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltranfebrer.com
 */
@Module
public class DiscogsModule {

    public static final String BASE_URL = "https://api.discogs.com/";

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public DiscogsService provideDiscogsService(Retrofit retrofit) {
        return retrofit.create(DiscogsService.class);
    }
}
