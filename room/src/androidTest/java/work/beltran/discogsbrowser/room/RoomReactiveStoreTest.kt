package work.beltran.discogsbrowser.room

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class RoomReactiveStoreTest {

    lateinit var store: RoomReactiveStore

    @Before
    fun setUp() {
        val database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                DiscoDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        store = RoomReactiveStore(database)
    }

    @Test
    fun add_record_while_subscribed() {
        val subscription = store.getAll().test()
        subscription.assertEmpty()
        // Add a single record
        val record = RecordRoom(
                id = 1,
                title = "Title",
                thumb = "Url",
                year = "2012"
        )
        store.replace(listOf(record))
        // subscription now has values and should be a list with the record
        subscription.assertValueCount(1)
        val value = subscription.values()[0]
        assertEquals(record, value[0])
    }

    @After
    fun tearDown() {

    }

}