package work.beltran.discogsbrowser.app.common

data class RecordListItem(
        val title: CharSequence = "",
        val artist: CharSequence = "",
        val format: CharSequence = "",
        val year: CharSequence = "",
        val thumb: String = "",
        val releaseId: Int = 0,
        val inCollection: Boolean = false
)

