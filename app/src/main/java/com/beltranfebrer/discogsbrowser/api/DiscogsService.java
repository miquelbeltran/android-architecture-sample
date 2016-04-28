package com.beltranfebrer.discogsbrowser.api;

import com.beltranfebrer.discogsbrowser.BuildConfig;
import com.beltranfebrer.discogsbrowser.api.model.RecordCollection;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by miquel on 22.04.16.
 */
public interface DiscogsService {
    // display https://api.discogs.com/users/mike513/collection/folders/0/releases
    @GET("users/{user}/collection/folders/0/releases")
    rx.Observable<RecordCollection> listRecords(@Path("user") String user, @Query("page") int page);
}