package work.beltran.discogsbrowser.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
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
        return rx.Observable.from(searchRecords).flatMap(new Func1<SearchRecord, rx.Observable<Record>>() {
            @Override
            public rx.Observable<Record> call(SearchRecord searchRecord) {
                Record record = new Record();
                record.setInstance_id(searchRecord.getId());
                BasicInformation basicInformation = new BasicInformation();
                String artist = searchRecord.title;
                String title = "";

                int index = searchRecord.title.indexOf(" - ");
                if (index > 0) {
                    title = searchRecord.title.substring(index + 3);
                    artist = searchRecord.title.substring(0, index);
                }

                Artist artistObject = new Artist();
                artistObject.setName(artist);
                List<Artist> artists = new ArrayList<>();
                artists.add(artistObject);
                basicInformation.setArtists(artists);

                List<Format> formats = new ArrayList<Format>();
                for(String formatString : searchRecord.format) {
                    Format format = new Format();
                    format.setName(formatString);
                    formats.add(format);
                }
                basicInformation.setFormats(formats);

                basicInformation.setTitle(title);
                basicInformation.setThumb(searchRecord.thumb);
                record.setBasicInformation(basicInformation);
                return Observable.just(record);
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
