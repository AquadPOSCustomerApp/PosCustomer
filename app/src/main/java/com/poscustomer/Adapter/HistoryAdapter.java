package com.poscustomer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.poscustomer.Application.SingleInstance;
import com.poscustomer.HistoryDetailActivity;
import com.poscustomer.Model.OrderHistory;
import com.poscustomer.Model.OrderItem;
import com.poscustomer.PhoneVerificationActivity;
import com.poscustomer.R;
import com.poscustomer.Register_Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DJ-PC on 7/6/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.DataHolder> {
    //private OrderHistory data;
    private OrderHistory listdata;
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

    public HistoryAdapter(OrderHistory listdata, Context c) {
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
        OrderHistory.Data item = listdata.getData().get(position);

        // OrderHistory.Data.OrderItems prod = listdata.getData().get(position).getOrder_items().get(0);
        // OrderHistory.Data item = listdata.getOrder.get(position);
        //OrderHistory.Data item = listdata.get(position);
        String paymentMode = item.getPay_mode();
        if (paymentMode.equals("Cash")) {
            holder.img_payment_mode.setImageResource(R.drawable.money);
        } else {
            holder.img_payment_mode.setImageResource(R.drawable.paytm_icon);
        }
        holder.tv_date_time.setText(item.getCreated_at());
        holder.tv_order_id.setText("ORDER_" + item.getOrder_id());
        holder.tv_description.setText("Total Item Purchased : " + item.getOrder_items().size());
        holder.tv_cost.setText(item.getGrand_total());
    }

    @Override
    public int getItemCount() {
        return listdata.getData().size();
    }


    class DataHolder extends RecyclerView.ViewHolder {
        TextView tv_date_time, tv_order_id, tv_description, tv_cost;
        ImageView img_payment_mode;
        CardView card_history_item;

        public DataHolder(final View itemView) {
            super(itemView);
            tv_date_time = (TextView) itemView.findViewById(R.id.tv_date_time);
            tv_order_id = (TextView) itemView.findViewById(R.id.tv_order_id);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            tv_cost = (TextView) itemView.findViewById(R.id.tv_cost);
            card_history_item = (CardView) itemView.findViewById(R.id.card_history_item);
            img_payment_mode = (ImageView) itemView.findViewById(R.id.img_payment_mode);
            card_history_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderHistory.Data item = listdata.getData().get(getLayoutPosition());
                    SingleInstance.getInstance().setHistoryDetails(item);
                    Intent intent = new Intent(context, HistoryDetailActivity.class);
                    intent.putExtra("orderId", item.getOrder_id());
                    intent.putExtra("totalCost", item.getGrand_total());
                    intent.putExtra("orderDate", item.getCreated_at());
                    context.startActivity(intent);

                }
            });
        }


    }

}
