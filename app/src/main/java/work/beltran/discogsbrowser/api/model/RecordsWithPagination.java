package work.beltran.discogsbrowser.api.model;

import java.util.List;

import work.beltran.discogsbrowser.api.model.pagination.Pagination;
import work.beltran.discogsbrowser.api.model.record.Record;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
public interface RecordsWithPagination {
    Pagination getPagination();
    List<Record> getRecords();
}
