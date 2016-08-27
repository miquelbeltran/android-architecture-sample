package work.beltran.discogsbrowser.api.model.record;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Miquel Beltran on 24.04.16.
 * More on http://beltran.work
 */
public class Format  implements Parcelable {
    private String name;

    public Format(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Format> CREATOR = new Creator<Format>() {
        @Override
        public Format createFromParcel(Parcel in) {
            return new Format(in);
        }

        @Override
        public Format[] newArray(int size) {
            return new Format[size];
        }
    };

    public Format() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
