package work.beltran.discogsbrowser.api.model.record;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

/**
 * Created by Miquel Beltran on 22.04.16
 * More on http://beltran.work
 */
@AutoValue
public abstract class Record {
    @SerializedName("id")
    public abstract int getId();

    @SerializedName("instance_id")
    @Nullable
    public abstract Integer getInstanceId();

    @SerializedName("basic_information")
    public abstract BasicInformation getBasicInformation();

    public static TypeAdapter<Record> typeAdapter(Gson gson) {
        return new AutoValue_Record.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_Record.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setId(int newId);

        public abstract Builder setInstanceId(Integer newInstanceId);

        public abstract Builder setBasicInformation(BasicInformation newBasicInformation);

        public abstract Record build();
    }
}
