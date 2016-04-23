package com.beltranfebrer.discogsbrowser.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.beltranfebrer.discogsbrowser.App;
import com.beltranfebrer.discogsbrowser.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.records_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecordsAdapter adapter = new RecordsAdapter();
        if (recyclerView != null) {
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
        App.getComponent().inject(adapter);
    }
}
