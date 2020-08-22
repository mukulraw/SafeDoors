package com.technobrix.tbx.safedoors.Inventory_List;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.InventryListPOJO.InventryList;
import com.technobrix.tbx.safedoors.R;

import java.util.ArrayList;
import java.util.List;


public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder> {


    Context context;
    List<InventryList>list = new ArrayList<>();

    public InventoryAdapter(Context context , List<InventryList>list){

        this.list = list;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.inventory_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        InventryList item = list.get(position);

        holder.name.setText(item.getName());
        holder.one.setText(String.valueOf(position + 1));
        holder.price.setText("Rs." + item.getPricePer());

    }

    public void setgrid(List<InventryList>list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name , one , price , qty;


        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            one = (TextView)itemView.findViewById(R.id.one);
            price = (TextView)itemView.findViewById(R.id.price);
            qty = (TextView)itemView.findViewById(R.id.quantity);
        }
    }
}
