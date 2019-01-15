package work.beltran.discogsbrowser.collection.data


import arrow.core.orNull
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response
import work.beltran.discogsbrowser.api.*

class GetCollectionUseCaseImplTest {

    @Test
    fun `get a collection page`() {
        runBlocking {
            val service = mockk<DiscogsService>()
            val element = Release(
                id = "id",
                basicInfo = BasicInfo(
                    title = "title",
                    thumb = "thumb",
                    artists = listOf(
                        work.beltran.discogsbrowser.common.domain.ArtistApi(
                            id = "id",
                            name = "name"
                        )
                    )
                )
            )
            val collectionItemsByFolderResponse = CollectionItemsByFolderResponse(
                pagination = Pagination(
                    perPage = 20,
                    pages = 1,
                    page = 1,
                    items = 1
                ),
                releases = listOf(
                    element
                )
            )

            every {
                service.getCollectionItemsByFolder(
                    username = "mike513",
                    folderId = "0",
                    page = 1,
                    perPage = 20
                )
            } returns CompletableDeferred(
                Response.success(
                    collectionItemsByFolderResponse
                )
            )

            val usecase = GetCollectionUseCaseImpl(service)
            val result = usecase.getCollectionPage("0", 1)
            val pagination = result.orNull()!!

            assert(pagination.currentPage == 1)
            assert(pagination.nextPage == null)
            assert(pagination.totalItems == 1)
            assert(pagination.data[0].id == "id")
            assert(pagination.data[0].title == "title")
            assert(pagination.data[0].thumb == "thumb")
            assert(pagination.data[0].artist.head.name == "name")
        }
    }

}