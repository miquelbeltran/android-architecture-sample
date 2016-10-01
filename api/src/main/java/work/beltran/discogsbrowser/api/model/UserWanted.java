package work.beltran.discogsbrowser.api.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import work.beltran.discogsbrowser.api.model.pagination.Pagination;
import work.beltran.discogsbrowser.api.model.record.Record;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
@AutoValue
public abstract class UserWanted implements RecordsWithPagination {
    @SerializedName("pagination")
    public abstract Pagination getPagination();
    @SerializedName("wants")
    public abstract List<Record> getRecords();

    public static TypeAdapter<UserWanted> typeAdapter(Gson gson) {
        return new AutoValue_UserWanted.GsonTypeAdapter(gson);
    }
}
