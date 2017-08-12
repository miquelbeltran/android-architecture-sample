package work.beltran.discogsbrowser.api.model.record

import com.google.auto.value.AutoValue
import com.google.gson.Gson
import com.google.gson.TypeAdapter

/**
 * Created by Miquel Beltran on 24.04.16.
 * More on http://beltran.work
 */
@AutoValue
abstract class Artist {
    abstract val name: String

    @AutoValue.Builder
    abstract class Builder {
        abstract fun name(name: String): Builder
        abstract fun build(): Artist
    }

    companion object {

        fun typeAdapter(gson: Gson): TypeAdapter<Artist> {
            return AutoValue_Artist.GsonTypeAdapter(gson)
        }

        fun builder(): Builder {
            return AutoValue_Artist.Builder()
        }
    }
}
