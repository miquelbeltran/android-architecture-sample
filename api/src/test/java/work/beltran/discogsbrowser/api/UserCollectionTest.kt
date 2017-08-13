package work.beltran.discogsbrowser.api

import org.junit.Test

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
class UserCollectionTest : DiscogsServiceTest() {

    @Test
    fun `get user collection list`() {
        val subscriber = service.listRecords(DiscogsServiceTest.USER, PAGE).test()
        subscriber.assertNoErrors()
        subscriber.assertComplete()
    }

    companion object {
        private const val PAGE = 0
    }
}
