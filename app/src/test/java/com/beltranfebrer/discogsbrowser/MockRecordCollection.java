package com.beltranfebrer.discogsbrowser;

import com.beltranfebrer.discogsbrowser.network.model.Record;
import com.beltranfebrer.discogsbrowser.network.model.RecordCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltranfebrer.com
 */
public class MockRecordCollection {
    public RecordCollection recordCollection = new RecordCollection();

    public MockRecordCollection() {
        List<Record> records = new ArrayList<>();
        Record record = new Record();
        record.instance_id = "1234";
        records.add(record);
        recordCollection.setRecords(records);
    }
}
