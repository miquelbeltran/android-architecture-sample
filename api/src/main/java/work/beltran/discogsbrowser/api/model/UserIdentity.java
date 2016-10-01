package work.beltran.discogsbrowser.api.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by Miquel Beltran on 03.05.16.
 * More on http://beltran.work
 */
@AutoValue
public abstract class UserIdentity {
    public abstract int getId();
    public abstract String getUsername();

    public static TypeAdapter<UserIdentity> typeAdapter(Gson gson) {
        return new AutoValue_UserIdentity.GsonTypeAdapter(gson);
    }
}
