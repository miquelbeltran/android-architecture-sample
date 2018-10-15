package work.beltran.discogsbrowser.api

import com.squareup.moshi.Json

data class CollectionItemsByFolderResponse(
    val pagination: Pagination,
    val releases: List<Release>
)

data class Release(
    val id: String
)

data class Pagination(
    @Json(name = "per_page") val perPage: Int,
    val pages: Int,
    val page: Int,
    val items: Int
)
