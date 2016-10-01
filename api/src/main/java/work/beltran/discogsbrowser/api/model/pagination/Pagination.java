package work.beltran.discogsbrowser.api.model.pagination;


import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Miquel Beltran on 22.04.16
 * More on http://beltran.work
 */
@AutoValue
public abstract class Pagination {
    @SerializedName("page")
    public abstract int getPage();
    @SerializedName("pages")
    public abstract int getPages();

    public static TypeAdapter<Pagination> typeAdapter(Gson gson) {
        return new AutoValue_Pagination.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_Pagination.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder page(int page);
        public abstract Builder pages(int pages);
        public abstract Pagination build();
    }
}
