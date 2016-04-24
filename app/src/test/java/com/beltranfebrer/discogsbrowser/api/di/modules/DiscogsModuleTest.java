package com.beltranfebrer.discogsbrowser.api.di.modules;

import com.beltranfebrer.discogsbrowser.api.model.Record;
import com.beltranfebrer.discogsbrowser.api.model.RecordCollection;
import com.beltranfebrer.discogsbrowser.api.DiscogsService;

import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Miquel Beltran on 23/4/16.
 * More on http://beltranfebrer.com
 */
public class DiscogsModuleTest {
    DiscogsService service;
    @Before
    public void setUp() throws Exception {
        DiscogsModule discogsModule = new DiscogsModule();
        service = discogsModule.provideDiscogsService(discogsModule.provideRetrofit());
    }

    // Needs network!
    @Test
    public void testService() throws Exception {
        TestSubscriber<RecordCollection> subscriber = new TestSubscriber<>();
        service.listRecords("mike513").subscribe(subscriber);
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
