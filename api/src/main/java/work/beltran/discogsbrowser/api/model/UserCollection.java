package work.beltran.discogsbrowser.api.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import work.beltran.discogsbrowser.api.model.pagination.Pagination;
import work.beltran.discogsbrowser.api.model.record.Record;

/**
 * Created by Miquel Beltran on 22.04.16
 * More on http://beltran.work
 */
@AutoValue
public abstract class UserCollection implements RecordsWithPagination {
    @SerializedName("pagination")
    public abstract Pagination getPagination();

    @SerializedName("releases")
    public abstract List<Record> getRecords();

    public static TypeAdapter<UserCollection> typeAdapter(Gson gson) {
        return new AutoValue_UserCollection.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_UserCollection.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setPagination(Pagination newPagination);

        public abstract Builder setRecords(List<Record> newRecords);

        public abstract UserCollection build();
    }
}
