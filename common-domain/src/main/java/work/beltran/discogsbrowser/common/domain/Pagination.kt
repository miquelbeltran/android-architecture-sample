package work.beltran.discogsbrowser.common.domain

data class Pagination<T>(
    val data: T,
    val currentPage: Int,
    val nextPage: Int?,
    val totalItems: Int
)

