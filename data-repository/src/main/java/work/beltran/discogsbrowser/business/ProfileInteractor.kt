package work.beltran.discogsbrowser.business

import io.reactivex.Single
import work.beltran.discogsbrowser.api.model.UserIdentity
import work.beltran.discogsbrowser.api.model.UserProfile

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
interface ProfileInteractor {
    val identity: Single<UserIdentity>
    val profile: Single<UserProfile>
}
