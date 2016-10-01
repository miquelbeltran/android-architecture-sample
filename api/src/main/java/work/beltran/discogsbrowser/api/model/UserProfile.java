package work.beltran.discogsbrowser.api.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
@AutoValue
public abstract class UserProfile {
    @SerializedName("username")
    public abstract String getUsername();
    @SerializedName("num_collection")
    public abstract int getNumCollection();
    @SerializedName("avatar_url")
    public abstract String getAvatarUrl();
    @SerializedName("num_wantlist")
    public abstract int getNumWantlist();

    public static TypeAdapter<UserProfile> typeAdapter(Gson gson) {
        return new AutoValue_UserProfile.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_UserProfile.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setUsername(String newUsername);

        public abstract Builder setNumCollection(int newNumCollection);

        public abstract Builder setAvatarUrl(String newAvatarUrl);

        public abstract Builder setNumWantlist(int newNumWantlist);

        public abstract UserProfile build();
    }
}
