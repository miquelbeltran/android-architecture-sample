package work.beltran.discogsbrowser.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by miquel on 22.04.16.
 */
public class RecordCollection {
    @SerializedName("pagination")
    private Pagination pagination;
    @SerializedName("releases")
    private List<Record> records;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
