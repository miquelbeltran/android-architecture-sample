package work.beltran.discogsbrowser.api

import com.squareup.moshi.Moshi

interface DiscogsServiceBuilder {
    fun provideDiscogsService(moshi: Moshi): DiscogsService
}