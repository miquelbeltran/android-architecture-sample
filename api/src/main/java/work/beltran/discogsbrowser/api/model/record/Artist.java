package work.beltran.discogsbrowser.api.model.record;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import work.beltran.discogsbrowser.api.model.pagination.Pagination;

/**
 * Created by Miquel Beltran on 24.04.16.
 * More on http://beltran.work
 */
@AutoValue
public abstract class Artist {
    public abstract String getName();

    public static TypeAdapter<Artist> typeAdapter(Gson gson) {
        return new AutoValue_Artist.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_Artist.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder name(String name);
        public abstract Artist build();
    }
}
