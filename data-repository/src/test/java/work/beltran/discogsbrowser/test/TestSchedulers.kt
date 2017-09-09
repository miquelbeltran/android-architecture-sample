package work.beltran.discogsbrowser.test

import io.reactivex.schedulers.Schedulers
import work.beltran.discogsbrowser.business.RxJavaSchedulers

object TestSchedulers : RxJavaSchedulers {
    override fun io() = Schedulers.trampoline()
    override fun mainThread() = Schedulers.trampoline()
}