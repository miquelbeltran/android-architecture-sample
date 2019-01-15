package work.beltran.discogsbrowser.common.domain

import arrow.data.NonEmptyList
import work.beltran.discogsbrowser.api.Release
import work.beltran.discogsbrowser.api.ReleaseDetails

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

fun ReleaseDetails.toAlbum(): Album {
    return Album (
        id = id,
        title = title,
        thumb = thumb,
        artist = artists.toDomain()
    )
}

