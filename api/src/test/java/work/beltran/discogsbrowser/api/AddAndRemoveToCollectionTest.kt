package work.beltran.discogsbrowser.api

import org.junit.Test


/**
 * Created by Miquel Beltran on 10/2/16
 * More on http://beltran.work
 */
class AddAndRemoveToCollectionTest : DiscogsServiceTest() {

    @Test
    fun `add and remove record to collection`() {
        // Add to collection
        val testSubscriber = service.addToCollection(DiscogsServiceTest.USER, RELEASE_ID).test()
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()

        val userCollectionTestSubscriber = service.getRecordInCollection(DiscogsServiceTest.USER, RELEASE_ID).test()
        userCollectionTestSubscriber.assertNoErrors()
        userCollectionTestSubscriber.assertComplete()
        val userCollection = userCollectionTestSubscriber.values()[0]
        for (record in userCollection.records) {
            // Delete each instance from collection
            val test = service.removeFromCollection(DiscogsServiceTest.USER, RELEASE_ID, record.instanceId!!).test()
            test.assertNoErrors()
            test.assertComplete()
        }
    }

    companion object {
        private const val RELEASE_ID = 4855808
    }
}
