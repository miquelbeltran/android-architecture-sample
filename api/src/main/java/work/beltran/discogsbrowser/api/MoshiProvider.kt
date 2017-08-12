package work.beltran.discogsbrowser.api

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi

/**
 * Created by Miquel Beltran on 10/1/16
 * More on http://beltran.work
 */
object MoshiProvider {
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
    }
}
