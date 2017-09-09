package work.beltran.discogsbrowser.room

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class DiscoDatabaseTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: DiscoDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                DiscoDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @Test
    fun add_record_while_subscribed() {
        val tester = database.recordDao().getAll().test()
        // Initially the database is empty
        tester.assertValue { it.isEmpty() }
        // Add a single record
        val record = RecordRoom(
                id = 1,
                title = "Title",
                thumb = "Url",
                year = "2012"
        )
        database.recordDao().replace(record)
        // Now the received value contains our record
        tester.assertValueAt(1) { it.contains(record) }
    }

    @After
    fun tearDown() {
        database.close()
    }
}