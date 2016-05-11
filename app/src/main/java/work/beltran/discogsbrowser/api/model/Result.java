package work.beltran.discogsbrowser.api.model;

import java.util.List;

/**
 * Created by Miquel Beltran on 10.05.16.
 * More on http://beltran.work
 */
public class Result {
    int id;
    String thumb;
    String title;
    List<String> format;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getFormat() {
        return format;
    }

    public void setFormat(List<String> format) {
        this.format = format;
    }
}
