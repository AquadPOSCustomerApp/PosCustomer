package com.poscustomer;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.poscustomer.Adapter.GetOfferAdapter;
import com.poscustomer.Adapter.HistoryAdapter;
import com.poscustomer.Application.OffersInstance;
import com.poscustomer.Application.SingleInstance;
import com.poscustomer.Model.GetOffers;
import com.poscustomer.Model.OrderHistory;

public class GetAllOffersActivity extends CustomActivity {
    private Toolbar toolbar;
    private RecyclerView rec_get_al_offers;
    private GetOffers listdata;
   // private GetOfferAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_offers);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Get All Offers");
        actionBar.setTitle("");


        rec_get_al_offers = (RecyclerView) findViewById(R.id.rec_get_al_offers);

        //listdata = SingleInstance.getInstance().getHistoryData();
        listdata = OffersInstance.getInstance().getOffersData();

        rec_get_al_offers.setLayoutManager(new LinearLayoutManager(this));
       // adapter = new GetOfferAdapter(listdata, this);
      //  rec_get_al_offers.setAdapter(adapter);

    }
}
