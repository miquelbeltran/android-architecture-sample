package work.beltran.discogsbrowser.api.model.record;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Miquel Beltran on 24.04.16.
 * More on http://beltran.work
 */
@AutoValue
public abstract class Format {
    public abstract String getName();

    public static TypeAdapter<Format> typeAdapter(Gson gson) {
        return new AutoValue_Format.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_Format.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setName(String newName);

        public abstract Format build();
    }
}
