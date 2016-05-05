package work.beltran.discogsbrowser.currency;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by Miquel Beltran on 05.05.16.
 * More on http://beltran.work
 */
@Module
public class FixerModule {
    public static final String BASE_URL = "https://api.fixer.io/";

    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public FixerService provideFixerService() {
        return provideRetrofit().create(FixerService.class);
    }
}
