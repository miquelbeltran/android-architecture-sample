package work.beltran.discogsbrowser.collection.data

import arrow.data.NonEmptyList
import work.beltran.discogsbrowser.api.Release
import work.beltran.discogsbrowser.common.domain.Album
import work.beltran.discogsbrowser.common.domain.Artist

fun List<Release>.toAlbums(): List<Album> {
    return map {release ->
        Album(
            id = release.id,
            title = release.basicInfo.title,
            thumb = release.basicInfo.thumb,
            artist = release.basicInfo.artists.toDomain()
        )
    }
}

typealias ArtistApi = work.beltran.discogsbrowser.api.Artist

fun List<ArtistApi>.toDomain(): NonEmptyList<Artist> {
    return NonEmptyList.fromListUnsafe(map { artist ->
        Artist(
            id = artist.id,
            name = artist.name
        )
    })
}

