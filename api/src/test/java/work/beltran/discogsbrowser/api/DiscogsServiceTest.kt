package work.beltran.discogsbrowser.api


import org.junit.Before

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
abstract class DiscogsServiceTest {
    lateinit var service: DiscogsService

    @Before
    fun setUp() {
        val apiKey = System.getenv("DISCOGS_API_KEY")
        val builder = DiscogsServiceBuilderWithKey(apiKey, "Unit Test")
        service = builder.provideDiscogsService(MoshiProvider.provideMoshi())
    }

    companion object {
        internal const val USER = "mike513"
    }
}
