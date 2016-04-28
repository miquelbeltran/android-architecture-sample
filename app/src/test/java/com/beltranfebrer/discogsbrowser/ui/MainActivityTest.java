package com.beltranfebrer.discogsbrowser.ui;

import android.support.v7.widget.RecyclerView;

import com.beltranfebrer.discogsbrowser.BuildConfig;
import com.beltranfebrer.discogsbrowser.api.model.Record;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltranfebrer.com
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
    MainActivity activity;
    RecordsAdapter adapter;

    @Before
    public void setUp() throws Exception {
//        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
//        adapter = mock(RecordsAdapter.class);
//        activity.setAdapter(adapter);
    }
    
    @Test
    public void testAttached() throws Exception {
//        verify(adapter).onAttachedToRecyclerView((RecyclerView) any());
    }

    @Test
    public void testName() throws Exception {


    }
}
