package work.beltran.discogsbrowser.currency;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class FixerServiceBuilder {
    public static final String BASE_URL = "https://api.fixer.io/";

    public FixerService provideFixerService() {
        return provideRetrofit().create(FixerService.class);
    }

    private Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
