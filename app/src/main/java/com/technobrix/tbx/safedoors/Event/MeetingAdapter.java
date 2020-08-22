package com.technobrix.tbx.safedoors.Event;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.GetAllMeetingPOJO.MeetingList;
import com.technobrix.tbx.safedoors.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tvs on 10/31/2017.
 */

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MyViewHolder> {


    Context context;

    List<MeetingList>list = new ArrayList<>();

    public MeetingAdapter(Context context ,  List<MeetingList>list){

        this.context = context;
        this.list = list;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.mee_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        MeetingList item = list.get(position);

        holder.s.setText(String.valueOf(position + 1));
        holder.d.setText(item.getMeetingDate());
        holder.t.setText(item.getTitle());
        holder.time.setText(item.getMeetingTime());


    }

    public void setgrid(List<MeetingList>list){


        this.list = list;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView s , d , t , time;

        public MyViewHolder(View itemView) {
            super(itemView);

            s = (TextView)itemView.findViewById(R.id.one);
            d = (TextView)itemView.findViewById(R.id.date);
            t = (TextView)itemView.findViewById(R.id.title);
            time = (TextView)itemView.findViewById(R.id.time);
        }
    }
}
