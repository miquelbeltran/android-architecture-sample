package work.beltran.discogsbrowser.api.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by Miquel Beltran on 10.05.16.
 * More on http://beltran.work
 */
@AutoValue
public abstract class SearchRecord {
    public abstract int getId();

    public abstract String getThumb();

    public abstract String getTitle();

    @Nullable
    public abstract String getYear();

    @Nullable
    public abstract List<String> getFormat();

    public static TypeAdapter<SearchRecord> typeAdapter(Gson gson) {
        return new AutoValue_SearchRecord.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_SearchRecord.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setId(int newId);

        public abstract Builder setThumb(String newThumb);

        public abstract Builder setTitle(String newTitle);

        public abstract Builder setYear(String newYear);

        public abstract Builder setFormat(List<String> newFormat);

        public abstract SearchRecord build();
    }
}
