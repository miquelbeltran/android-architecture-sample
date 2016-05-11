package work.beltran.discogsbrowser.api.model;

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
public class Results implements RecordsWithPagination {
    Pagination pagination;
    List<Result> results;

    public Pagination getPagination() {
        return pagination;
    }

    @Override
    public List<Record> getRecords() {
        return rx.Observable.from(results).flatMap(new Func1<Result, rx.Observable<Record>>() {
            @Override
            public rx.Observable<Record> call(Result result) {
                Record record = new Record();
                record.setInstance_id(result.getId());
                BasicInformation basicInformation = new BasicInformation();
                String artist = result.title;
                String title = "";

                int index = result.title.indexOf(" - ");
                if (index > 0) {
                    title = result.title.substring(index + 3);
                    artist = result.title.substring(0, index);
                }

                Artist artistObject = new Artist();
                artistObject.setName(artist);
                List<Artist> artists = new ArrayList<>();
                artists.add(artistObject);
                basicInformation.setArtists(artists);

                List<Format> formats = new ArrayList<Format>();
                for(String formatString : result.format) {
                    Format format = new Format();
                    format.setName(formatString);
                    formats.add(format);
                }
                basicInformation.setFormats(formats);

                basicInformation.setTitle(title);
                basicInformation.setThumb(result.thumb);
                record.setBasicInformation(basicInformation);
                return Observable.just(record);
            }
        }).toList().toBlocking().single();
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
