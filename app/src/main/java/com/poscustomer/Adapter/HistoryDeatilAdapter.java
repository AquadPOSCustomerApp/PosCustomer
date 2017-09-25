package com.poscustomer.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.poscustomer.Model.OrderHistory;
import com.poscustomer.R;

/**
 * Created by DJ-PC on 7/6/2017.
 */

public class HistoryDeatilAdapter extends RecyclerView.Adapter<HistoryDeatilAdapter.DataHolder> {
    //private OrderHistory data;
    private OrderHistory.Data listdata;
    private LayoutInflater inflater;
    private ItemClickCallback itemclickcallback;
    private int count = 0;
    private Context context;
    private Float totalPrice, itemPrice;
    private int itemQty;
    private String strTotalPrice;

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

    public HistoryDeatilAdapter(OrderHistory.Data listdata, Context c) {
        this.inflater = LayoutInflater.from(c);
        this.listdata = listdata;
        this.context = c;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.history_item_detail, parent, false);
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
        OrderHistory.Data.OrderItems item = listdata.getOrder_items().get(position);

        itemPrice = Float.parseFloat(item.getPrice().toString());
        itemQty = Integer.parseInt(item.getItem_quantity().toString());
        totalPrice = (itemPrice * itemQty);
        strTotalPrice = String.valueOf(totalPrice);
        holder.tv_item_name.setText(item.getName());
        holder.tv_item_quantity.setText(" X " + item.getItem_quantity());
        holder.tv_total_item_price.setText(strTotalPrice);
        holder.tv_item_price.setText(item.getPrice());
    }

    @Override
    public int getItemCount() {
        return listdata.getOrder_items().size();
    }


    class DataHolder extends RecyclerView.ViewHolder {
        TextView tv_item_name, tv_item_quantity, tv_total_item_price, tv_item_price;


        public DataHolder(final View itemView) {
            super(itemView);
            tv_item_name = (TextView) itemView.findViewById(R.id.tv_item_name);
            tv_item_quantity = (TextView) itemView.findViewById(R.id.tv_item_quantity);
            tv_total_item_price = (TextView) itemView.findViewById(R.id.tv_total_item_price);
            tv_item_price = (TextView) itemView.findViewById(R.id.tv_item_price);

        }


    }

}
