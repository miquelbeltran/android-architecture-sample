package work.beltran.discogsbrowser.api.network.login;

/**
 * Created by Miquel Beltran on 14.05.16.
 * More on http://beltran.work
 */
public class RequestHeader extends LoginHeader {
    private static final String CALLBACK = "discogs://callback";

    public RequestHeader(String consumerKey, String consumerSecret) {
        super(consumerKey, consumerSecret + "&");
    }

    @Override
    public String getHeader() {
        return super.getHeader() + ", " + "oauth_callback=\"" + CALLBACK +"\"";
    }
}
