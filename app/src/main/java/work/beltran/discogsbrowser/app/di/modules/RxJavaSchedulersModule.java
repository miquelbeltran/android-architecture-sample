package work.beltran.discogsbrowser.app.di.modules;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
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
            @NotNull
            @Override
            public Scheduler computation() {
                return null;
            }

            @Override
            public Scheduler io() {
                return Schedulers.io();
            }

            @Override
            public Scheduler mainThread() {
                return null;
//                return Schedulers.mainThread();
            }
        };
    }
}
