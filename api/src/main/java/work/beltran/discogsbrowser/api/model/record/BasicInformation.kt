package work.beltran.discogsbrowser.api.model.record

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
data class BasicInformation(
        val title: String,
        val year: String,
        val thumb: String,
        val formats: List<Format>,
        val artists: List<Artist>
)
