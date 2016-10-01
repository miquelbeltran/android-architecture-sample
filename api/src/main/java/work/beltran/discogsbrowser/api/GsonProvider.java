package work.beltran.discogsbrowser.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Miquel Beltran on 10/1/16
 * More on http://beltran.work
 */
public class GsonProvider {
    public static Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(DiscogsAdapterFactory.create())
                .create();
    }
}
