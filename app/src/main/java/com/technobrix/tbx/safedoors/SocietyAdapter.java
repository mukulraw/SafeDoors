package com.technobrix.tbx.safedoors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.GetHelpPOJO.HelpList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tvs on 10/16/2017.
 */

public class SocietyAdapter extends RecyclerView.Adapter<SocietyAdapter.MyViewHolder> {

    Context context;

    List<HelpList> list = new ArrayList<>();

    public SocietyAdapter(Context context , List<HelpList> list){

        this.context = context;

        this.list = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.society_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        HelpList item = list.get(position);
        holder.name.setText(item.getNumber());
        holder.num.setText(item.getNumber());

    }

    public void setgrid(List<HelpList>list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name , num;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            num = (TextView)itemView.findViewById(R.id.number);
        }
    }
}
