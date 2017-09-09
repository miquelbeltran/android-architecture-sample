package work.beltran.discogsbrowser.business.login

/**
 * Created by Miquel Beltran on 14.05.16.
 * More on http://beltran.work
 */
class RequestHeader(consumerKey: String,
                    consumerSecret: String)
    : LoginHeader(consumerKey, consumerSecret + "&") {

    override val header = super.header + ", oauth_callback=\"discogs://callback\""
}
