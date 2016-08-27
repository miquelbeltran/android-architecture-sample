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
        DiscogsServiceBuilderWithKey builder = new DiscogsServiceBuilderWithKey("api key", "Unit Test");
        service = builder.provideDiscogsService();
    }
}
