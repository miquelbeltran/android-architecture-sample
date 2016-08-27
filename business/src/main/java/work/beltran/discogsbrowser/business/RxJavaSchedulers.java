package work.beltran.discogsbrowser.business;

import rx.Scheduler;

/**
 * Created by Miquel Beltran on 15.05.16.
 * More on http://beltran.work
 */
public interface RxJavaSchedulers {
    Scheduler io();

    Scheduler mainThread();
}
