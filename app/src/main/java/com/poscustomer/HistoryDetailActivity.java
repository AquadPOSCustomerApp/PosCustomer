package com.poscustomer;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.poscustomer.Adapter.HistoryAdapter;
import com.poscustomer.Adapter.HistoryDeatilAdapter;
import com.poscustomer.Application.DetailSingleInstance;
import com.poscustomer.Application.SingleInstance;
import com.poscustomer.Model.OrderHistory;

public class HistoryDetailActivity extends CustomActivity {
    private Toolbar toolbar;
    private TextView tv_order_id, tv_total_price, tv_order_date;
    private RecyclerView rec_history_detail;
    private HistoryDeatilAdapter adapter;
    private OrderHistory.Data listdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Items Detail");
        actionBar.setTitle("");

        tv_order_id =(TextView)findViewById(R.id.tv_order_id);
        tv_total_price=(TextView)findViewById(R.id.tv_total_price);
        tv_order_date=(TextView)findViewById(R.id.tv_order_date);

        tv_order_id.setText("ORDER_ "+getIntent().getStringExtra("orderId"));
        tv_total_price.setText("Rs. "+getIntent().getStringExtra("totalCost"));
        tv_order_date.setText(getIntent().getStringExtra("orderDate"));

        rec_history_detail = (RecyclerView) findViewById(R.id.rec_history_detail);
        //listdata = DetailSingleInstance.getInstance().getItemData();

        listdata = SingleInstance.getInstance().getHistoryDetails();


        rec_history_detail.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryDeatilAdapter(listdata, this);
        rec_history_detail.setAdapter(adapter);


    }
}
