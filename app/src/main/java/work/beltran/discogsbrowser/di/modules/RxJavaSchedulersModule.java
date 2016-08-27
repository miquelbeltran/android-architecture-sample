package work.beltran.discogsbrowser.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import work.beltran.discogsbrowser.business.RxJavaSchedulers;

/**
 * Created by Miquel Beltran on 15.05.16.
 * More on http://beltran.work
 */
@Module
public class RxJavaSchedulersModule {
    @Provides
    @Singleton
    public RxJavaSchedulers provideSchedulers() {
        return new RxJavaSchedulers() {
            @Override
            public Scheduler io() {
                return Schedulers.io();
            }

            @Override
            public Scheduler mainThread() {
                return AndroidSchedulers.mainThread();
            }
        };
    }
}
