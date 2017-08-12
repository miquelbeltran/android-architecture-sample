package work.beltran.discogsbrowser.api.model.record

import com.squareup.moshi.Json

/**
 * Created by Miquel Beltran on 22.04.16
 * More on http://beltran.work
 */
data class Record (
        val id: Int,
        @Json(name = "instance_id")
        val instanceId: Int?,
        @Json(name = "basic_information")
        val basicInformation: BasicInformation
)
