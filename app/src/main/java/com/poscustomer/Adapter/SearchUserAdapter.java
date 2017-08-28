package com.poscustomer.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.poscustomer.Model.RestUser;
import com.poscustomer.Model.UserSearch;
import com.poscustomer.R;
import com.poscustomer.SearchUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DJ-PC on 4/29/2017.
 */

public class SearchUserAdapter extends RecyclerView.Adapter<SearchUserAdapter.Holder> {

    private List<UserSearch> horizontalList;
    private Context mContext;




     public SearchUserAdapter(Context context, List<UserSearch> horizontalList) {
         this.horizontalList = horizontalList;
         mContext = context;
     }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_searh_user, parent, false);
        return new Holder(itemView);

    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
 holder.Email.setText(horizontalList.get(position).getEmail()+"\n"+horizontalList.get(position).getName()+"\n"+ horizontalList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView Email;

        public Holder(View itemView) {
            super(itemView);
            Email=(TextView)itemView.findViewById(R.id.user_email);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
