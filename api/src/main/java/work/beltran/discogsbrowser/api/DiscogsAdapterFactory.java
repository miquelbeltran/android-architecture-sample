package work.beltran.discogsbrowser.api;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by Miquel Beltran on 10/1/16
 * More on http://beltran.work
 */
@GsonTypeAdapterFactory
abstract class DiscogsAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create() {
        return new AutoValueGson_DiscogsAdapterFactory();
    }
}
