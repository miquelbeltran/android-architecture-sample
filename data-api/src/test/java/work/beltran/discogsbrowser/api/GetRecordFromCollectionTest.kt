package work.beltran.discogsbrowser.api

import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

/**
 * Created by Miquel Beltran on 10/2/16
 * More on http://beltran.work
 */
class GetRecordFromCollectionTest : DiscogsServiceTest() {

    @Test
    fun `get record from collection`() {
        val testSubscriber = service.getRecordInCollection(DiscogsServiceTest.USER, RECORD_ID).test()
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()
        val userCollection = testSubscriber.values()[0]
        assertThat(userCollection.records.size).isEqualTo(1)
    }

    @Test
    fun `empty results on get record if not found`() {
        val testSubscriber = service.getRecordInCollection(DiscogsServiceTest.USER, RECORD_ID_NOT_FOUND).test()
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()
        val userCollection = testSubscriber.values()[0]
        assertThat(userCollection.records.size).isEqualTo(0)
    }

    companion object {
        private const val RECORD_ID = 6095128
        private const val RECORD_ID_NOT_FOUND = 1
    }
}
