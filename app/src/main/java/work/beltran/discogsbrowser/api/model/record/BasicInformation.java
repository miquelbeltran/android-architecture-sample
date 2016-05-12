package work.beltran.discogsbrowser.api.model.record;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
public class BasicInformation implements Parcelable {
    private String title;
    private String year;
    private String thumb;
    private List<Format> formats;
    private List<Artist> artists;

    public BasicInformation(Parcel in) {
        title = in.readString();
        year = in.readString();
        thumb = in.readString();
        formats = in.readArrayList(Format.class.getClassLoader());
        artists = in.readArrayList(Artist.class.getClassLoader());
    }

    public static final Creator<BasicInformation> CREATOR = new Creator<BasicInformation>() {
        @Override
        public BasicInformation createFromParcel(Parcel in) {
            return new BasicInformation(in);
        }

        @Override
        public BasicInformation[] newArray(int size) {
            return new BasicInformation[size];
        }
    };

    public BasicInformation() {

    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Format> getFormats() {
        return formats;
    }

    public void setFormats(List<Format> formats) {
        this.formats = formats;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(thumb);
        dest.writeArray(formats.toArray());
        dest.writeArray(artists.toArray());
    }
}
