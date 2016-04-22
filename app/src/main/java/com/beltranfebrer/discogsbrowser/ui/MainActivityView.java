package com.beltranfebrer.discogsbrowser.ui;

import com.beltranfebrer.discogsbrowser.network.model.Record;

import java.util.List;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltranfebrer.com
 */
public interface MainActivityView {
    void showRecords(List<Record> records);
}
