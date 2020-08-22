package com.technobrix.tbx.safedoors;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.BookPOJO.FacilityBookList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tvs on 11/17/2017.
 */

public class BookAdpetr extends RecyclerView.Adapter<BookAdpetr.MyViewHolder> {

    Context context;
    List<FacilityBookList>list = new ArrayList<>();


    public BookAdpetr(Context context ,  List<FacilityBookList>list){

        this.context = context;
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.book_list_model , parent , false);

        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {



        FacilityBookList item = list.get(position);

        holder.name.setText(item.getFacilityName());
        holder.fromtime.setText(item.getFromTime());
        holder.time.setText(item.getToTime());
        holder.des.setText(item.getDescription());



    }


    public void setgrid( List<FacilityBookList>list){


        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name , time  , fromtime , des;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.facility);
            time = (TextView)itemView.findViewById(R.id.time1);
            fromtime = (TextView)itemView.findViewById(R.id.time);
            des = (TextView)itemView.findViewById(R.id.des);
        }
    }
}
