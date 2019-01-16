package work.beltran.discogsbrowser.album.domain

import arrow.core.orNull
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Response
import work.beltran.discogsbrowser.api.DiscogsService
import work.beltran.discogsbrowser.api.ReleaseDetails

class GetAlbumDetailsUseCaseImplTest {

    @Test
    fun `get album details`() {
        runBlocking {
            val service = mockk<DiscogsService>()
            val response = ReleaseDetails(
                id = "id",
                title = "title",
                thumb = "thumb",
                artists = listOf(
                    work.beltran.discogsbrowser.common.domain.ArtistApi(
                        id = "id",
                        name = "name"
                    )
                )
            )
            every {
                service.getRelease(releaseId = "id")
            } returns CompletableDeferred(
                Response.success(response)
            )

            val usecase = GetAlbumDetailsUseCaseImpl(service)
            val result = usecase.getAlbum(id = "id").orNull()!!

            assertEquals("id", result.id)
            assertEquals("title", result.title)
            assertEquals("thumb", result.thumb)
            assertEquals("name", result.artist.head.name)
        }
    }
}