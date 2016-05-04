package work.beltran.discogsbrowser.api.model;

import java.util.ArrayList;
import java.util.List;

import work.beltran.discogsbrowser.api.model.pagination.Pagination;
import work.beltran.discogsbrowser.api.model.record.Artist;
import work.beltran.discogsbrowser.api.model.record.BasicInformation;
import work.beltran.discogsbrowser.api.model.record.Format;
import work.beltran.discogsbrowser.api.model.record.Record;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
public class MockRecordCollection {
    public UserCollection userCollection = new UserCollection();

    public MockRecordCollection() {
        List<Record> records = new ArrayList<>();
        Record record = new Record();
        record.setInstance_id("1234");

        Pagination pagination = new Pagination();
        pagination.setPages(2);
        pagination.setPage(1);
        pagination.setItems(2);
        userCollection.setPagination(pagination);

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
        userCollection.setRecords(records);
    }
}
