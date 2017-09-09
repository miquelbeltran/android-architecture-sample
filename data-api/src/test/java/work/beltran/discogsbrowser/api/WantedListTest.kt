package work.beltran.discogsbrowser.api

import org.junit.Test

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
class WantedListTest : DiscogsServiceTest() {

    @Test
    fun `get wanted list`() {
        val subscriber = service.getWantedList(USER, PAGE).test()
        subscriber.assertNoErrors()
        subscriber.assertComplete()
    }

    companion object {
        private const val USER = "mike513"
        private const val PAGE = 0
    }
}
