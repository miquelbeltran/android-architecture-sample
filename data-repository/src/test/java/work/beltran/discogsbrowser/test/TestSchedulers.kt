package work.beltran.discogsbrowser.test

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import work.beltran.discogsbrowser.business.RxJavaSchedulers

object TestSchedulers : RxJavaSchedulers {
    override fun computation() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
    override fun mainThread() = Schedulers.trampoline()
}