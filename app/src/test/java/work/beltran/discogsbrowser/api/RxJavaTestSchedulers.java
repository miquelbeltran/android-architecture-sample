package work.beltran.discogsbrowser.api;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by Miquel Beltran on 15.05.16.
 * More on http://beltran.work
 */
public class RxJavaTestSchedulers implements RxJavaSchedulers {
    @Override
    public Scheduler io() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler mainThread() {
        return Schedulers.immediate();
    }
}
