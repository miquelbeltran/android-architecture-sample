package work.beltran.discogsbrowser.api.model.record;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
@AutoValue
public abstract class BasicInformation {
    public abstract String getTitle();
    public abstract String getYear();
    public abstract String getThumb();
    public abstract List<Format> getFormats();
    public abstract List<Artist> getArtists();

    public static TypeAdapter<BasicInformation> typeAdapter(Gson gson) {
        return new AutoValue_BasicInformation.GsonTypeAdapter(gson);
    }
    public static Builder builder() {
        return new AutoValue_BasicInformation.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder title(String title);
        public abstract Builder year(String year);
        public abstract Builder thumb(String thumb);
        public abstract Builder formats(List<Format> formats);
        public abstract Builder artists(List<Artist> artists);
        public abstract BasicInformation build();
    }
}
