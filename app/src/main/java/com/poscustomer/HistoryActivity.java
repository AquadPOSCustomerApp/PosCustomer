package com.poscustomer;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.poscustomer.Adapter.DummyHistoryData;
import com.poscustomer.Adapter.HistoryAdapter;
import com.poscustomer.Application.SingleInstance;
import com.poscustomer.Model.OrderHistory;

import java.util.ArrayList;

public class HistoryActivity extends CustomActivity {
    private Toolbar toolbar;
    private RecyclerView history_rec;
    private OrderHistory listdata;
    private HistoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("History");
        actionBar.setTitle("");

        history_rec = (RecyclerView) findViewById(R.id.history_rec);

        listdata = SingleInstance.getInstance().getHistoryData();


        history_rec.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryAdapter(listdata, this);
        history_rec.setAdapter(adapter);

    }
}
