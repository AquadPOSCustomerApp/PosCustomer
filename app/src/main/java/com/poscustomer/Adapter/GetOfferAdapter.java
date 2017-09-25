package com.poscustomer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.poscustomer.Application.SingleInstance;
import com.poscustomer.HistoryDetailActivity;
import com.poscustomer.Model.GetOffers;
import com.poscustomer.Model.OrderHistory;
import com.poscustomer.R;

/**
 * Created by DJ-PC on 7/6/2017.
 */

public class GetOfferAdapter extends RecyclerView.Adapter<GetOfferAdapter.DataHolder> {

    private GetOffers listdata;
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

    public GetOfferAdapter(GetOffers listdata, Context c) {
        this.inflater = LayoutInflater.from(c);
        this.listdata = listdata;
        this.context = c;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.get_offers_item, parent, false);
        return new DataHolder(view);


    }




    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        GetOffers.Data item = listdata.getData().get(position);

      /*  holder.tv_offer_id.setText(item.getOffer_id());
        holder.tv_rest_id.setText(item.getRestaurant_id());
        holder.tv_offer_name.setText(item.getOffer_name());
        holder.tv_offer_descp.setText(item.getOffer_description());
        holder.tv_offer_price.setText(item.getOffer_price());
        holder.tv_discount_value.setText(item.getDiscount_value());
        holder.tv_actual_rate.setText(item.getActual_rate());
        holder.tv_offer_image.setText(item.getOffer_image());
        holder.tv_total_offer_count.setText(item.getTotal_offer_count());
        holder.tv_max_per_user.setText(item.getMax_per_user());
        holder.tv_total_likes.setText(item.getTotal_likes());
        holder.tv_remaining_count.setText(item.getRemaining_count());
        holder.tv_rate_after_discount.setText(item.getRate_after_discount());
        holder.tv_offer_status.setText(item.getOffer_status());
        holder.tv_status.setText(item.getStatus());
        holder.tv_created_at.setText(item.getCreated_at());
        holder.tv_updated_at.setText(item.getUpdated_at());
        holder.tv_name.setText(item.getName());*/

    }

    @Override
    public int getItemCount() {
        return listdata.getData().size();
    }


    class DataHolder extends RecyclerView.ViewHolder {
        TextView tv_offer_id, tv_rest_id, tv_offer_name, tv_offer_descp, tv_offer_price, tv_discount_value, tv_actual_rate, tv_offer_image, tv_total_offer_count, tv_max_per_user, tv_total_likes, tv_remaining_count, tv_rate_after_discount, tv_offer_status, tv_status, tv_created_at, tv_updated_at, tv_name;



        public DataHolder(final View itemView) {
            super(itemView);
         /*   tv_offer_id = (TextView) itemView.findViewById(R.id.tv_offer_id);
            tv_rest_id = (TextView) itemView.findViewById(R.id.tv_rest_id);
            tv_offer_name = (TextView) itemView.findViewById(R.id.tv_offer_name);
            tv_offer_descp = (TextView) itemView.findViewById(R.id.tv_offer_descp);
            tv_offer_price = (TextView) itemView.findViewById(R.id.tv_offer_price);
            tv_discount_value = (TextView) itemView.findViewById(R.id.tv_discount_value);
            tv_actual_rate = (TextView) itemView.findViewById(R.id.tv_actual_rate);
            tv_offer_image = (TextView) itemView.findViewById(R.id.tv_offer_image);
            tv_total_offer_count = (TextView) itemView.findViewById(R.id.tv_total_offer_count);
            tv_max_per_user = (TextView) itemView.findViewById(R.id.tv_max_per_user);
            tv_total_likes = (TextView) itemView.findViewById(R.id.tv_total_likes);
            tv_remaining_count = (TextView) itemView.findViewById(R.id.tv_remaining_count);
            tv_rate_after_discount = (TextView) itemView.findViewById(R.id.tv_rate_after_discount);
            tv_offer_status = (TextView) itemView.findViewById(R.id.tv_offer_status);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_created_at = (TextView) itemView.findViewById(R.id.tv_created_at);
            tv_updated_at = (TextView) itemView.findViewById(R.id.tv_updated_at);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);*/


        }


    }

}
