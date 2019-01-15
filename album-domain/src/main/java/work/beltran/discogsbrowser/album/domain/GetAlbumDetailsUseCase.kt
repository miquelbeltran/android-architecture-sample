package work.beltran.discogsbrowser.album.domain

import arrow.core.Either
import arrow.core.Try
import retrofit2.Response
import work.beltran.discogsbrowser.api.DiscogsService
import work.beltran.discogsbrowser.api.ReleaseDetails
import work.beltran.discogsbrowser.common.domain.Album
import work.beltran.discogsbrowser.common.domain.toAlbum

class GetAlbumDetailsUseCaseImpl(
    val service: DiscogsService
) {

    suspend fun getAlbum(id: String): Either<String, Album> {
        return Try {
            requestItem(id)
        }.fold({
            processException(it)
        }, {
            processResult(it)
        })
    }

    private suspend fun requestItem(id: String): Response<ReleaseDetails> {
        return service.getRelease(id).await()
    }

    private fun processException(it: Throwable) = Either.left(it.localizedMessage)

    private fun processResult(
        result: Response<ReleaseDetails>
    ): Either<String, Album> {
        val body = result.body()
        return if (result.isSuccessful && body != null) {
            Either.right(body.toAlbum())
        } else {
            Either.left(result.errorBody()?.string() ?: "Unknown Error")
        }
    }
}

