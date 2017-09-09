package work.beltran.discogsbrowser.business.login

/**
 * Created by Miquel Beltran on 14.05.16.
 * More on http://beltran.work
 */
class AccessHeader(consumerKey: String,
                   consumerSecret: String,
                   userToken: String,
                   verifier: String)
    : LoginHeader(consumerKey, consumerSecret) {

    override val header = super.header + ", oauth_verifier=\"$verifier\", oauth_token=\"$userToken\""
}
