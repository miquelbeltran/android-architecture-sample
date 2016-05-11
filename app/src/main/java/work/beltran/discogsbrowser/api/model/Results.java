package work.beltran.discogsbrowser.api.model;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import work.beltran.discogsbrowser.api.model.pagination.Pagination;
import work.beltran.discogsbrowser.api.model.record.BasicInformation;
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
                basicInformation.setTitle(result.title);
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
