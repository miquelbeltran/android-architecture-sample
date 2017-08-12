package work.beltran.discogsbrowser.api.model

import com.squareup.moshi.Json

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
data class UserProfile(
        val username: String,
        @Json(name = "num_collection")
        val numCollection: Int,
        @Json(name = "avatar_url")
        val avatarUrl: String,
        @Json(name = "num_wantlist")
        val numWantlist: Int
)
