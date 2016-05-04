package work.beltran.discogsbrowser.api.model;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
public class UserProfile {
    String username;
    int num_collection;
    String avatar_url;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNum_collection() {
        return num_collection;
    }

    public void setNum_collection(int num_collection) {
        this.num_collection = num_collection;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
