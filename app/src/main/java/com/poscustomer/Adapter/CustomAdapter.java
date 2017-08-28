package com.poscustomer.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.poscustomer.Model.DummyListItem;
import com.poscustomer.R;

import java.util.ArrayList;

import static android.R.attr.resource;

/**
 * Created by DJ-PC on 5/6/2017.
 */

public class CustomAdapter extends ArrayAdapter<DummyListItem> {
  private ArrayList<DummyListItem>dataset;
    Context mContext;

    public CustomAdapter(ArrayList<DummyListItem> data, Context context) {
        super(context, R.layout.item_last_purchase, data);
        this.dataset = data;
        this.mContext= context;
    }

    private static class ViewHolder{
        TextView Time;
        TextView Cost;
    }

   private int lastPosition = -1 ;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DummyListItem dum = getItem(position);

        ViewHolder viewHolder;

        final  View result;
         if(convertView == null){
             viewHolder= new ViewHolder();
             LayoutInflater inflater = LayoutInflater.from(getContext());
             convertView= inflater.inflate(R.layout.item_last_purchase,parent, false);
             viewHolder.Time=(TextView)convertView.findViewById(R.id.tv_date_time);
             viewHolder.Cost=(TextView)convertView.findViewById(R.id.tv_cost);
             result=convertView;
             convertView.setTag(viewHolder);
         }else {
             viewHolder=(ViewHolder)convertView.getTag();
             result=convertView;;
         }


        viewHolder.Time.setText(dum.getTime());
        viewHolder.Cost.setText(dum.getCost());
        return convertView;



    }
}
