package work.beltran.discogsbrowser.common.domain

import arrow.data.NonEmptyList

data class Album(
    val id: String,
    val title: String,
    val artist: NonEmptyList<Artist>,
    val thumb: String
)

data class Artist(
    val id: String,
    val name: String
)

