package work.beltran.discogsbrowser.api

import org.junit.Test
import org.assertj.core.api.Assertions.assertThat


/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
class UserProfileTest : DiscogsServiceTest() {

    @Test
    fun `get user profile`() {
        val tester = service.getUserProfile(USER).test()
        tester.assertNoErrors()
        tester.assertComplete()
        assertThat(tester.values()[0].username).isEqualTo(USER)
    }
}
