package work.beltran.discogsbrowser.app.common;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;
import java.util.List;

import work.beltran.discogsbrowser.api.model.record.Artist;
import work.beltran.discogsbrowser.api.model.record.Format;
import work.beltran.discogsbrowser.api.model.record.Record;

/**
 * Created by Miquel Beltran on 10/1/16
 * More on http://beltran.work
 */
@AutoValue
public abstract class RecordViewModel {
    public abstract CharSequence getTitle();

    public abstract CharSequence getArtist();

    public abstract CharSequence getFormat();

    public abstract CharSequence getYear();

    public abstract String getThumb();

    public static Builder builder() {
        return new AutoValue_RecordViewModel.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setTitle(CharSequence newTitle);

        public abstract Builder setArtist(CharSequence newArtist);

        public abstract Builder setFormat(CharSequence newFormat);

        public abstract Builder setYear(CharSequence newYear);

        public abstract Builder setThumb(String newThumb);

        public abstract RecordViewModel build();
    }

    public static RecordViewModel createRecordViewModel(Record record) {
        StringBuilder artistString = new StringBuilder();
        for (Artist artist : record.getBasicInformation().getArtists()) {
            if (artistString.length() > 0) {
                artistString.append(", ");
            }
            artistString.append(artist.getName());
        }
        StringBuilder formatString = new StringBuilder();
        for (Format format : record.getBasicInformation().getFormats()) {
            if (formatString.length() > 0) {
                formatString.append(", ");
            }
            formatString.append(format.getName());
        }
        String year = record.getBasicInformation().getYear();
        if (year.equals("0")) {
            year = "";
        }
        return RecordViewModel.builder()
                .setArtist(artistString.toString())
                .setFormat(formatString.toString())
                .setThumb(record.getBasicInformation().getThumb())
                .setTitle(record.getBasicInformation().getTitle())
                .setYear(year)
                .build();
    }

    public static List<RecordViewModel> createRecordsList(List<Record> records) {
        List<RecordViewModel> list = new ArrayList<>();
        for (Record record : records) {
            list.add(RecordViewModel.createRecordViewModel(record));
        }
        return list;
    }
}
