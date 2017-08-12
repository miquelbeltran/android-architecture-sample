package work.beltran.discogsbrowser.api.model

import org.junit.Test
import work.beltran.discogsbrowser.api.model.pagination.Pagination
import org.assertj.core.api.Assertions.assertThat

/**
 * Created by Miquel Beltran on 10/1/16
 * More on http://beltran.work
 */
class SearchResultsTest {
    lateinit var searchResults: SearchResults

    @Test
    fun `get records from search result`(){
        val pagination = Pagination(
                page = 0,
                pages = 1
        )
        val list = listOf(SearchRecord(
                format = listOf(FORMAT),
                id = ID,
                thumb = THUMB,
                title = "$ARTIST - $TITLE",
                year = YEAR
        ))
        searchResults = SearchResults(
                searchRecords = list,
                pagination = pagination
        )
        val basicInformation = searchResults.records[0].basicInformation
        assertThat(basicInformation.artists.size).isEqualTo(1)
        assertThat(basicInformation.artists[0].name).isEqualTo(ARTIST)
        assertThat(basicInformation.title).isEqualTo(TITLE)
        assertThat(basicInformation.formats.size).isEqualTo(1)
        assertThat(basicInformation.formats[0].name).isEqualTo(FORMAT)
        assertThat(basicInformation.formats.size).isEqualTo(1)
        assertThat(basicInformation.formats[0].name).isEqualTo(FORMAT)
        assertThat(basicInformation.year).isEqualTo(YEAR)
        assertThat(searchResults.records[0].id).isEqualTo(ID)
    }

    companion object {
        private val FORMAT = "FORMAT"
        private val ID = 1234
        private val THUMB = "THUMB"
        private val TITLE = "TITLE"
        private val ARTIST = "ARTIST"
        private val YEAR = "1999"
    }
}