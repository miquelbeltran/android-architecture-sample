package work.beltran.discogsbrowser.business

import io.reactivex.Single
import work.beltran.discogsbrowser.api.model.UserWanted

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
interface WantedInteractor {
    fun getWanted(page: Int): Single<UserWanted>
}
