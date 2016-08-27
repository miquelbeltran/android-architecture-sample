package work.beltran.discogsbrowser.api.model.pagination;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by miquel on 22.04.16.
 */
public class Pagination implements Parcelable {
    @SerializedName("page")
    private int page;
    @SerializedName("pages")
    private int pages;
    private int items;

    public Pagination(Parcel in) {
        page = in.readInt();
        pages = in.readInt();
        items = in.readInt();
    }

    public Pagination() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeInt(pages);
        dest.writeInt(items);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pagination> CREATOR = new Creator<Pagination>() {
        @Override
        public Pagination createFromParcel(Parcel in) {
            return new Pagination(in);
        }

        @Override
        public Pagination[] newArray(int size) {
            return new Pagination[size];
        }
    };

    public int getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }
}
