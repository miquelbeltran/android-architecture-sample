package work.beltran.discogsbrowser.api

import org.junit.Test

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
class UserIdentityTest : DiscogsServiceTest() {

    @Test
    fun `get user identity`() {
        val subscriber = service.userIdentity.test()
        subscriber.assertNoErrors()
        subscriber.assertComplete()
    }
}
