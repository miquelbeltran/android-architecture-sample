package work.beltran.discogsbrowser.collection

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
import arrow.core.Either
import arrow.data.NonEmptyList
import com.android21buttons.fragmenttestrule.FragmentTestRule
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin
import work.beltran.discogsbrowser.common.domain.Album
import work.beltran.discogsbrowser.common.domain.Artist
import work.beltran.discogsbrowser.common.domain.GetCollectionUseCase
import work.beltran.discogsbrowser.common.domain.Pagination


@RunWith(AndroidJUnit4::class)
class CollectionFragmentTest {

    @get:Rule
    val fragmentTestRule = FragmentTestRule.create(CollectionFragment::class.java, true, false)

    @Test
    fun load_single_item_page() {

        // Mock the use case to return a single item
        val collectionUseCase = mockUseCaseForSingleItem()

        // Configure dependency injection to provide ViewModel with Use Case
        configureKoin(collectionUseCase)

        // Launch Fragment in a Test Activity
        launchFragment()

        // Assert Album title is displayed in the list
        onView(withText("title")).check(matches(isDisplayed()))
    }

    private fun launchFragment() {
        val fragment = CollectionFragment()
        fragmentTestRule.launchFragment(fragment)
    }

    private fun configureKoin(collectionUseCase: GetCollectionUseCase) {
        val collectionKoinModule = module {
            viewModel { CollectionViewModel(collectionUseCase) }
        }
        startKoin(listOf(collectionKoinModule))
    }

    private fun mockUseCaseForSingleItem(): GetCollectionUseCase {
        val collectionUseCase = mockk<GetCollectionUseCase>()
        val album = Album(
            id = "id",
            title = "title",
            thumb = "thumb",
            artist = NonEmptyList.just(Artist(id = "id", name = "name"))
        )
        every {
            runBlocking {
                collectionUseCase.getCollectionPage("0", 1)
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
        return collectionUseCase
    }


}
