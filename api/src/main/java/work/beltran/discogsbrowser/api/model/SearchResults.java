package work.beltran.discogsbrowser.api.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
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
@AutoValue
public abstract class SearchResults implements RecordsWithPagination {
    public abstract Pagination getPagination();
    @SerializedName("results")
    public abstract List<SearchRecord> getSearchRecords();

    @Override
    public List<Record> getRecords() {
        return rx.Observable
                .from(getSearchRecords())
                .map(new Func1<SearchRecord, Record>() {
            @Override
            public Record call(SearchRecord searchRecord) {
                String artist = searchRecord.getTitle();
                String title = "";

                int index = searchRecord.getTitle().indexOf(" - ");
                if (index > 0) {
                    title = searchRecord.getTitle().substring(index + 3);
                    artist = searchRecord.getTitle().substring(0, index);
                }

                Artist artistObject = Artist.builder().name(artist).build();
                List<Artist> artists = new ArrayList<>();
                artists.add(artistObject);
                List<Format> formats = new ArrayList<>();
                if (searchRecord.getFormat() != null) {
                    for (String formatString : searchRecord.getFormat()) {
                        Format format = Format.builder().setName(formatString).build();
                        formats.add(format);
                    }
                }
                String year = searchRecord.getYear() != null ? searchRecord.getYear() : "";
                BasicInformation basicInformation = BasicInformation.builder()
                        .artists(artists)
                        .formats(formats)
                        .title(title)
                        .thumb(searchRecord.getThumb())
                        .year(year)
                        .build();
                return Record.builder()
                        .setInstanceId(searchRecord.getId())
                        .setBasicInformation(basicInformation)
                        .build();
            }
        }).toList().toBlocking().single();
    }

    public static TypeAdapter<SearchResults> typeAdapter(Gson gson) {
        return new AutoValue_SearchResults.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_SearchResults.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setPagination(Pagination newPagination);

        public abstract Builder setSearchRecords(List<SearchRecord> newSearchRecords);

        public abstract SearchResults build();
    }
}
