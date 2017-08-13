package work.beltran.discogsbrowser.business.search

import io.reactivex.Single
import org.junit.Before

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import work.beltran.discogsbrowser.api.DiscogsService
import work.beltran.discogsbrowser.api.model.SearchRecord
import work.beltran.discogsbrowser.api.model.SearchResults
import work.beltran.discogsbrowser.api.model.pagination.Pagination
import work.beltran.discogsbrowser.business.SearchInteractor
import work.beltran.discogsbrowser.test.*

@RunWith(MockitoJUnitRunner::class)
class SearchInteractorImplTest {

    lateinit var searchInteractor: SearchInteractor

    @Mock
    lateinit var service: DiscogsService

    @Before
    fun setUp() {
        searchInteractor = SearchInteractorImpl(service, TestSchedulers)
    }

    @Test
    fun `search query`() {
        `when`(service.search(QUERY, SearchInteractorImpl.SEARCH_TYPE, null, null)).thenReturn(Single.just(SearchResults(
                pagination = Pagination(0, 1),
                searchRecords = listOf(SEARCH_RECORD))))
        val tester = searchInteractor.search(QUERY).test()
        tester.assertNoErrors()
        tester.assertComplete()
        val value = tester.values()[0]
        assertThat(value[0].id).isEqualTo(RECORD_ID)
        assertThat(value[0].basicInformation.artists[0]).isEqualTo(ARTIST_1)
        assertThat(value[0].basicInformation.title).isEqualTo(TITLE)
    }

    companion object {
        private const val QUERY = "query"
    }
}