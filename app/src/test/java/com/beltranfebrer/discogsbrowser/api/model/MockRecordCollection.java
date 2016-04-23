package com.beltranfebrer.discogsbrowser.api.model;

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
        record.setInstance_id("1234");
        records.add(record);
        recordCollection.setRecords(records);
    }
}
