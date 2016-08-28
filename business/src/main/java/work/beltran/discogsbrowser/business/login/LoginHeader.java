package work.beltran.discogsbrowser.business.login;

import java.util.Date;

/**
 * Created by Miquel Beltran on 14.05.16.
 * More on http://beltran.work
 */
public class LoginHeader {
    private String consumerKey;
    private String consumerSecret;

    public LoginHeader(String consumerKey, String consumerSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
    }

    public String getHeader() {
        return "OAuth oauth_consumer_key=\"" + consumerKey + "\", " +
                "oauth_nonce=\"" + new Date().getTime() + "\", " +
                "oauth_signature=\"" + consumerSecret + "\", " +
                "oauth_signature_method=\"PLAINTEXT\", " +
                "oauth_timestamp=\"" + new Date().getTime() + "\"";
    }

}
