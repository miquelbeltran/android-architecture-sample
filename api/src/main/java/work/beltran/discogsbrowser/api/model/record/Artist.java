package work.beltran.discogsbrowser.api.model.record;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Miquel Beltran on 24.04.16.
 * More on http://beltran.work
 */
public class Artist implements Parcelable {
    private String name;

    public Artist(Parcel in) {
        name = in.readString();
    }

    public Artist() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
