package work.beltran.discogsbrowser.api.network.login;

/**
 * Created by Miquel Beltran on 14.05.16.
 * More on http://beltran.work
 */
public class AccessHeader extends LoginHeader {
    private String userToken;
    private String verifier;

    public AccessHeader(String consumerKey, String consumerSecret, String userToken, String verifier) {
        super(consumerKey, consumerSecret);
        this.userToken = userToken;
        this.verifier = verifier;
    }

    @Override
    public String getHeader() {
        return super.getHeader() + ", "
                + "oauth_verifier=\"" + verifier + "\", "
                + "oauth_token=\"" + userToken + "\"";
    }
}
