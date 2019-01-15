package work.beltran.discogsbrowser.api

data class ReleaseDetails(
    val title: String,
    val id: String,
    val thumb: String,
    val artists: List<Artist>
)

