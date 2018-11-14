package work.beltran.discogsbrowser.collection.data

import arrow.core.Either
import arrow.core.Try
import retrofit2.Response
import work.beltran.discogsbrowser.api.CollectionItemsByFolderResponse
import work.beltran.discogsbrowser.api.DiscogsService
import work.beltran.discogsbrowser.api.provideService
import work.beltran.discogsbrowser.common.domain.Album
import work.beltran.discogsbrowser.common.domain.Pagination

fun provideGetCollectionUseCase(): GetCollectionUseCase {
    return GetCollectionUseCase(provideService())
}

class GetCollectionUseCase(
    private val discogsService: DiscogsService
) {

    suspend fun getCollectionPage(
        folder: String,
        page: Int
    ): Either<String, Pagination<List<Album>>> {

        return Try {
            requestItems(folder, page)
        }.fold({
            processException(it)
        }, { result ->
            processResult(result, page)
        })
    }

    private suspend fun requestItems(
        folder: String,
        page: Int
    ): Response<CollectionItemsByFolderResponse> {
        return discogsService.getCollectionItemsByFolder(
            username = "mike513",
            folderId = folder,
            page = page,
            perPage = PAGE_SIZE
        ).await()
    }

    private fun processException(it: Throwable) = Either.left(it.localizedMessage)

    private fun processResult(
        result: Response<CollectionItemsByFolderResponse>,
        page: Int
    ): Either<String, Pagination<List<Album>>> {
        return if (result.isSuccessful && result.body() != null) {
            val albums = result.body()!!.releases.toAlbums()
            val pagination = result.body()!!.pagination
            val nextPage = if (pagination.page < pagination.pages) {
                pagination.page + 1
            } else {
                null
            }
            Either.right(Pagination(albums, page, nextPage, pagination.items))
        } else {
            Either.left(result.errorBody()!!.string())
        }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
