package work.beltran.discogsbrowser.api;

import org.junit.Before;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class DiscogsServiceTest {
    DiscogsService service;

    @Before
    public void setUp() throws Exception {
        String apiKey = System.getenv("DISCOGS_API_KEY");
        DiscogsServiceBuilderWithKey builder = new DiscogsServiceBuilderWithKey(apiKey, "Unit Test");
        service = builder.provideDiscogsService();
    }
}
