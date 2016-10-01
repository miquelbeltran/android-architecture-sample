package work.beltran.discogsbrowser.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;
import work.beltran.discogsbrowser.api.model.pagination.Pagination;
import work.beltran.discogsbrowser.api.model.record.Artist;
import work.beltran.discogsbrowser.api.model.record.BasicInformation;
import work.beltran.discogsbrowser.api.model.record.Format;
import work.beltran.discogsbrowser.api.model.record.Record;

/**
 * Created by Miquel Beltran on 10.05.16.
 * More on http://beltran.work
 */
public class SearchResults implements RecordsWithPagination {
    Pagination pagination;
    @SerializedName("results")
    List<SearchRecord> searchRecords;

    public Pagination getPagination() {
        return pagination;
    }

    @Override
    public List<Record> getRecords() {
        return rx.Observable
                .from(searchRecords)
                .map(new Func1<SearchRecord, Record>() {
            @Override
            public Record call(SearchRecord searchRecord) {
                Record record = new Record();
                record.setInstance_id(searchRecord.getId());
                String artist = searchRecord.title;
                String title = "";

                int index = searchRecord.title.indexOf(" - ");
                if (index > 0) {
                    title = searchRecord.title.substring(index + 3);
                    artist = searchRecord.title.substring(0, index);
                }

                Artist artistObject = Artist.builder().name(artist).build();
                List<Artist> artists = new ArrayList<>();
                artists.add(artistObject);
                List<Format> formats = new ArrayList<>();
                for(String formatString : searchRecord.format) {
                    Format format = Format.builder().setName(formatString).build();
                    formats.add(format);
                }
                BasicInformation basicInformation = BasicInformation.builder()
                        .artists(artists)
                        .formats(formats)
                        .title(title)
                        .thumb(searchRecord.thumb)
                        .build();
                record.setBasicInformation(basicInformation);
                return record;
            }
        }).toList().toBlocking().single();
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<SearchRecord> getSearchRecords() {
        return searchRecords;
    }

    public void setSearchRecords(List<SearchRecord> searchRecords) {
        this.searchRecords = searchRecords;
    }
}
