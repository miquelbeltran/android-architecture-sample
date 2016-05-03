package work.beltran.discogsbrowser.api.di.modules;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import rx.observers.TestSubscriber;
import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.model.Record;
import work.beltran.discogsbrowser.api.model.RecordCollection;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.ui.TestApp;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Miquel Beltran on 23/4/16.
 * More on http://beltran.work
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, application = TestApp.class)
public class DiscogsModuleTest {
    DiscogsService service;
    @Before
    public void setUp() throws Exception {
        DiscogsModule discogsModule = new DiscogsModule(BuildConfig.API_KEY);
        service = discogsModule.provideDiscogsService(discogsModule.provideRetrofit());
    }

    @Test
    public void testUserIdentity() throws Exception {
        TestSubscriber<UserIdentity> subscriber = new TestSubscriber<>();
        service.getUserIdentity().subscribe(subscriber);
        subscriber.assertNoErrors();
        UserIdentity userIdentity = subscriber.getOnNextEvents().get(0);
        assertThat(userIdentity.getUsername()).matches("mike513");
    }

    // Needs network!
    @Test
    public void testService() throws Exception {
        TestSubscriber<RecordCollection> subscriber = new TestSubscriber<>();
        service.listRecords("mike513", 1).subscribe(subscriber);
        RecordCollection collection = subscriber.getOnNextEvents().get(0);
        assertThat(collection.getRecords().size()).isEqualTo(50);
        Record record = collection.getRecords().get(0);
        assertThat(record.getBasicInformation().getYear()).matches("0");
//        assertThat(record.getBasicInformation().getThumb()).matches("0");
        assertThat(record.getBasicInformation().getTitle()).matches("Reggatta De Blanc");
        assertThat(record.getBasicInformation().getArtists().get(0).getName()).matches("The Police");
        assertThat(record.getBasicInformation().getFormats().get(0).getName()).matches("Vinyl");
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }
}
