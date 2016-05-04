package work.beltran.discogsbrowser.api.model;

import java.util.List;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
public interface RecordsResult {
    Pagination getPagination();
    List<Record> getRecords();
}
