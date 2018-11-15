package work.beltran.discogsbrowser.collection

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Either
import arrow.data.NonEmptyList
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import work.beltran.discogsbrowser.collection.adapter.CollectionItem
import work.beltran.discogsbrowser.common.domain.Album
import work.beltran.discogsbrowser.common.domain.Artist
import work.beltran.discogsbrowser.common.domain.GetCollectionUseCase
import work.beltran.discogsbrowser.common.domain.Pagination

class CollectionViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    val useCase = mockk<GetCollectionUseCase>()
    val album = Album(
        id = "id",
        title = "title",
        thumb = "thumb",
        artist = NonEmptyList.just(Artist(id = "id", name = "name"))
    )

    @Test
    fun `load initial page`() {
        every {
            runBlocking {
                useCase.getCollectionPage("0", 1)
            }
        } returns Either.right(
            Pagination(
                data = listOf(
                    album
                ),
                currentPage = 1,
                nextPage = null,
                totalItems = 1
            )
        )

        val viewModel = CollectionViewModel(
            collectionUseCase = useCase,
            dispatcher = Dispatchers.Unconfined
        )

        val data = viewModel.liveData.value!!
        assertEquals(1, data.size)

        val item = data[0] as CollectionItem.AlbumItem
        assertEquals(album, item.album)
    }

    @Test
    fun `load second page`() {
        every {
            runBlocking {
                useCase.getCollectionPage("0", 1)
            }
        } returns Either.right(
            Pagination(
                data = listOf(
                    album
                ),
                currentPage = 1,
                nextPage = 2,
                totalItems = 2
            )
        )

        every {
            runBlocking {
                useCase.getCollectionPage("0", 2)
            }
        } returns Either.right(
            Pagination(
                data = listOf(
                    album
                ),
                currentPage = 2,
                nextPage = null,
                totalItems = 2
            )
        )

        // Loads page 1
        val viewModel = CollectionViewModel(
            collectionUseCase = useCase,
            dispatcher = Dispatchers.Unconfined
        )

        // Loads page 2
        viewModel.loadMore()


        val data = viewModel.liveData.value!!
        assertEquals(2, data.size)
    }
}