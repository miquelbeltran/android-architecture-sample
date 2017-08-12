package work.beltran.discogsbrowser.api.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import work.beltran.discogsbrowser.api.model.pagination.Pagination;
import work.beltran.discogsbrowser.api.model.record.BasicInformation;
import work.beltran.discogsbrowser.api.model.record.Record;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Miquel Beltran on 10/1/16
 * More on http://beltran.work
 */
public class SearchResultsTest {
    private static final String FORMAT = "FORMAT";
    private static final int ID = 1234;
    private static final String THUMB = "THUMB";
    private static final String TITLE = "TITLE";
    private static final String ARTIST = "ARTIST";
    private static final String YEAR = "1999";
    SearchResults searchResults;

    @Before
    public void setUp() throws Exception {
        Pagination pagination = Pagination.Companion.builder()
                .page(0)
                .pages(1)
                .build();
        List<SearchRecord> list = new ArrayList<>();
        List<String> formats = new ArrayList<>();
        formats.add(FORMAT);
        list.add(SearchRecord.builder()
                .setFormat(formats)
                .setId(ID)
                .setThumb(THUMB)
                .setTitle(ARTIST + " - " + TITLE)
                .setYear(YEAR)
                .build());
        searchResults = SearchResults.builder()
                .setSearchRecords(list)
                .setPagination(pagination)
                .build();
    }

    @Test
    public void getRecords() throws Exception {
        Record record = searchResults.getRecords().get(0);
        BasicInformation basicInformation = record.getBasicInformation();
        assertThat(basicInformation).isNotNull();
        assertThat(basicInformation.getArtists().size()).isEqualTo(1);
        assertThat(basicInformation.getArtists().get(0).getName()).isEqualTo(ARTIST);
        assertThat(basicInformation.getTitle()).isEqualTo(TITLE);
        assertThat(basicInformation.getFormats().size()).isEqualTo(1);
        assertThat(basicInformation.getFormats().get(0).getName()).isEqualTo(FORMAT);
        assertThat(basicInformation.getFormats().size()).isEqualTo(1);
        assertThat(basicInformation.getFormats().get(0).getName()).isEqualTo(FORMAT);
        assertThat(basicInformation.getYear()).isEqualTo(YEAR);
        assertThat(record.getId()).isEqualTo(ID);
    }
}