package work.beltran.discogsbrowser.api.model

/**
 * Created by Miquel Beltran on 10.05.16.
 * More on http://beltran.work
 */
data class SearchRecord(
        val id: Int,
        val thumb: String,
        val title: String,
        val year: String?,
        val format: List<String>?
)