package com.beltranfebrer.discogsbrowser.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltranfebrer.com
 */
public class MockRecordCollection {
    public RecordCollection recordCollection = new RecordCollection();

    public MockRecordCollection() {
        List<Record> records = new ArrayList<>();
        Record record = new Record();
        record.setInstance_id("1234");

        Pagination pagination = new Pagination();
        pagination.setPages(1);
        pagination.setPage(1);
        recordCollection.setPagination(pagination);

        BasicInformation basicInformation = new BasicInformation();
        basicInformation.setThumb("thumb url");
        basicInformation.setTitle("Title");
        basicInformation.setYear("2016");

        List<Artist> artists = new ArrayList<>();
        Artist artist = new Artist();
        artist.setName("Artist Name");
        artists.add(artist);
        basicInformation.setArtists(artists);

        List<Format> formats = new ArrayList<>();
        Format format = new Format();
        format.setName("CDr");
        formats.add(format);
        basicInformation.setFormats(formats);

        record.setBasicInformation(basicInformation);
        records.add(record);
        recordCollection.setRecords(records);
    }
}
