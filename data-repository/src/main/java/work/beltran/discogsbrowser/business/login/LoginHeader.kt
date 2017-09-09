package work.beltran.discogsbrowser.business.login

import java.util.Date

/**
 * Created by Miquel Beltran on 14.05.16.
 * More on http://beltran.work
 */
open class LoginHeader(private val consumerKey: String,
                       private val consumerSecret: String) {

    open val header: String
        get() = """
        OAuth oauth_consumer_key="$consumerKey",
        oauth_nonce="${Date().time}",
        oauth_signature="$consumerSecret",
        oauth_signature_method="PLAINTEXT",
        oauth_timestamp="${Date().time}"
"""
}
