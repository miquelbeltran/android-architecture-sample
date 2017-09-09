package work.beltran.discogsbrowser.business

import io.reactivex.Scheduler

/**
 * Created by Miquel Beltran on 15.05.16.
 * More on http://beltran.work
 */
interface RxJavaSchedulers {
    fun io(): Scheduler
    fun mainThread(): Scheduler
    fun computation(): Scheduler
}
