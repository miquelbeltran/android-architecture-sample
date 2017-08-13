package work.beltran.discogsbrowser.api

import org.junit.Test

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
class SearchTest : DiscogsServiceTest() {

    @Test
    fun `search using query`() {
        val subscriber = service.search(QUERY, null, null, null).test()
        subscriber.assertNoErrors()
        subscriber.assertComplete()
    }

    companion object {
        private const val QUERY = "String"
    }
}
