package work.beltran.discogsbrowser.api.model;

/**
 * Created by Miquel Beltran on 03.05.16.
 * More on http://beltran.work
 */
public class UserIdentity {
    private int id;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
