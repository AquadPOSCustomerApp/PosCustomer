package com.poscustomer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.poscustomer.Model.OrderHistory;
import com.poscustomer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DJ-PC on 7/6/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.DataHolder> {
    //private OrderHistory data;
    private List<OrderHistory> listdata;
    private LayoutInflater inflater;
    private ItemClickCallback itemclickcallback;
    private int count = 0;
    private Context context;
    public interface ItemClickCallback {
        void onItemClick(int p);

        void onSecondaryIconClick(int p);

    }







    public void SetItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemclickcallback = itemClickCallback;
    }
    /*public HistoryAdapter(OrderHistory data, Context c) {
        this.inflater = LayoutInflater.from(c);
        this.data = data;
        this.context = c;
    }*/

    public HistoryAdapter(List<OrderHistory> listdata, Context c) {
        this.inflater = LayoutInflater.from(c);
        this.listdata = listdata;
        this.context = c;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.history_item, parent, false);
        return new DataHolder(view);


    }


/*
    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        OrderHistory.Data item = data.getOrder().get(position);
        //   OrderHistory.Data item = listdata.getOrder.get(position);
        //OrderHistory.Data item = listdata.get(position);

        // holder.img_payment_mode.setImageResource(item.getPaymentModeIcon());
        holder.tv_date_time.setText(item.getCreated_at());
        holder.tv_order_id.setText(item.getOrder_id());
        // holder.tv_description.setText(item.getDescription());
        holder.tv_cost.setText(item.getGrand_total());
    }*/





    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        OrderHistory item = listdata.get(position);
             OrderHistory.Data abc = item.getOrder().get(position);
     //   OrderHistory.Data item = listdata.getOrder.get(position);
        //OrderHistory.Data item = listdata.get(position);

       // holder.img_payment_mode.setImageResource(item.getPaymentModeIcon());
        holder.tv_date_time.setText(abc.getCreated_at());
        holder.tv_order_id.setText(abc.getOrder_id());
       // holder.tv_description.setText(item.getDescription());
        holder.tv_cost.setText(abc.getGrand_total());
    }
    @Override
    public int getItemCount() {
        return listdata.size();
    }


    class DataHolder extends RecyclerView.ViewHolder {
        TextView tv_date_time,tv_order_id,tv_description,tv_cost;
        ImageView img_payment_mode;


        public DataHolder(final View itemView) {
            super(itemView);
            tv_date_time=(TextView)itemView.findViewById(R.id.tv_date_time);
            tv_order_id=(TextView)itemView.findViewById(R.id.tv_order_id);
            tv_description=(TextView)itemView.findViewById(R.id.tv_description);
            tv_cost=(TextView)itemView.findViewById(R.id.tv_cost);

            img_payment_mode=(ImageView)itemView.findViewById(R.id.img_payment_mode);

        }


    }

    public void setListData(ArrayList<OrderHistory> exerciseList) {
        this.listdata.clear();
        this.listdata.addAll(exerciseList);

    }
}
