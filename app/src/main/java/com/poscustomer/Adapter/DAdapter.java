package com.poscustomer.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mancj.slideup.SlideUp;
import com.poscustomer.Model.GetOffers;
import com.poscustomer.Model.ListItem;
import com.poscustomer.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DAdapter extends RecyclerView.Adapter<DAdapter.DataHolder> {


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


    public DAdapter(GetOffers listdata, Context c) {
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
        holder.tv_name.setText(item.getName());
        holder.tv_offer_descp.setText(item.getOffer_description());
        holder.tv_offer_price.setText(item.getOffer_price());
        holder.tv_actual_rate.setText(item.getActual_rate());
        holder.tv_offer_name.setText(item.getOffer_name());
        if(!TextUtils.isEmpty(item.getOffer_image())){
            Picasso.with(context).load(item.getOffer_image()).fit().centerCrop().into(holder.tv_offer_image);
        }
    }

    @Override
    public int getItemCount() {
        return listdata.getData().size();
    }


    class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_name, tv_offer_descp, tv_offer_price, tv_actual_rate, tv_offer_name;
        RelativeLayout rel_get_offers;
        LinearLayout lnr_offer_detail;
        ImageView tv_offer_image;

        public DataHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_offer_descp = (TextView) itemView.findViewById(R.id.tv_offer_descp);
            tv_offer_price = (TextView) itemView.findViewById(R.id.tv_offer_price);
            tv_actual_rate = (TextView) itemView.findViewById(R.id.tv_actual_rate);
            tv_offer_name = (TextView) itemView.findViewById(R.id.tv_offer_name);
            rel_get_offers = (RelativeLayout) itemView.findViewById(R.id.rel_get_offers);
            lnr_offer_detail = (LinearLayout) itemView.findViewById(R.id.lnr_offer_detail);
            tv_offer_image = (ImageView) itemView.findViewById(R.id.tv_offer_image);

            lnr_offer_detail.setOnClickListener(this);
            //  load = (TextView) itemView.findViewById(R.id.timestamp);
            rel_get_offers.setOnClickListener(this);
            //secondaryIcon = (ImageView) itemView.findViewById(R.id.im_item_icon_secondary);
            //secondaryIcon.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.rel_get_offers) {
                itemclickcallback.onItemClick(getAdapterPosition());
            } else {
                //itemclickcallback.onSecondaryIconClick(getAdapterPosition());
            }
        }
    }


}
