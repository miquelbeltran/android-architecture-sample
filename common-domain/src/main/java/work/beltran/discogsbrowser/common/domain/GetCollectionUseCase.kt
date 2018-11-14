package work.beltran.discogsbrowser.common.domain

import arrow.core.Either

interface GetCollectionUseCase {
    suspend fun getCollectionPage(
        folder: String,
        page: Int
    ): Either<String, Pagination<List<Album>>>
}

