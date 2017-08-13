package work.beltran.discogsbrowser.test

import work.beltran.discogsbrowser.api.model.SearchRecord
import work.beltran.discogsbrowser.api.model.record.Artist
import work.beltran.discogsbrowser.api.model.record.BasicInformation
import work.beltran.discogsbrowser.api.model.record.Format
import work.beltran.discogsbrowser.api.model.record.Record

val RECORD_ID = 1
val INSTANCE_ID = 2
val TITLE = "title"
val YEAR = "2017"
val THUMB = "url"
val FORMAT_1_NAME = "vinyl"
val FORMAT_1 = Format(FORMAT_1_NAME)
val FORMAT_2_NAME = "cd"
val FORMAT_2 = Format(FORMAT_2_NAME)
val FORMATS = listOf(FORMAT_1, FORMAT_2)
val ARTIST_1_NAME = "Artist 1"
val ARTIST_1 = Artist(ARTIST_1_NAME)
val ARTIST_2 = Artist("Artist 2")
val ARTISTS = listOf(ARTIST_1, ARTIST_2)
val BASICINFO = BasicInformation(
        title = TITLE,
        year = YEAR,
        thumb = THUMB,
        formats = FORMATS,
        artists = ARTISTS)
val RECORD = Record(
        id = RECORD_ID,
        instanceId = INSTANCE_ID,
        basicInformation = BASICINFO)
val SEARCH_RECORD = SearchRecord(
        id = RECORD_ID,
        thumb = THUMB,
        title = ARTIST_1_NAME + " - " + TITLE,
        year = YEAR,
        format = listOf(FORMAT_1_NAME, FORMAT_2_NAME)
)

