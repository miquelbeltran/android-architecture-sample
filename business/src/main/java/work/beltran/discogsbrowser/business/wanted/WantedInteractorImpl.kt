package work.beltran.discogsbrowser.business.wanted

import io.reactivex.Single
import work.beltran.discogsbrowser.api.DiscogsService
import work.beltran.discogsbrowser.api.model.UserWanted
import work.beltran.discogsbrowser.business.ProfileInteractor
import work.beltran.discogsbrowser.business.RxJavaSchedulers
import work.beltran.discogsbrowser.business.WantedInteractor

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
class WantedInteractorImpl(private val service: DiscogsService,
                           private val schedulers: RxJavaSchedulers,
                           private val profileInteractor: ProfileInteractor)
    : WantedInteractor {

    override fun getWanted(page: Int): Single<UserWanted> {
        return profileInteractor
                .identity
                .flatMap { service.getWantedList(it.username, page) }
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
    }
}
