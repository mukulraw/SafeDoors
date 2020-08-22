package com.technobrix.tbx.safedoors;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.technobrix.tbx.safedoors.NotificationListPOJO.NotificationList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.MyViewHolder> {

    Context context;
    List<NotificationList> list = new ArrayList<>();


    public NotiAdapter(Context context , List<NotificationList> list){

        this.context = context;
        this.list = list;
    }
    @Override
    public NotiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.notify_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotiAdapter.MyViewHolder holder, int position) {

        NotificationList item = list.get(position);

        if (Objects.equals(item.getNotifyData().getVisitorType(), "Regular"))
        {
            holder.name.setText(item.getNotifyData().getStaffName());

            if (Objects.equals(item.getType(), "visitor_out"))
            {
                holder.status.setText("Left");
            }
            else if (Objects.equals(item.getType(), "visitor_entry"))
            {
                holder.status.setText("Entered");
            }

        }
        else if (Objects.equals(item.getNotifyData().getVisitorType(), "New"))
        {
            holder.name.setText(item.getNotifyData().getVisitorName());

            if (Objects.equals(item.getType(), "visitor_out"))
            {
                holder.status.setText("Left");
            }
            else if (Objects.equals(item.getType(), "visitor_entry"))
            {
                holder.status.setText("Entered");
            }
        }



        holder.comment.setText(item.getNotifyData().getComment());
        holder.intime.setText(item.getNotifyData().getIntime());
        holder.outtime.setText(item.getNotifyData().getOuttime());


    }

    public void Setgrid(List<NotificationList>list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name , intime , outtime , status , comment;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            outtime = (TextView)itemView.findViewById(R.id.out);
            intime = (TextView)itemView.findViewById(R.id.in);
            status = (TextView)itemView.findViewById(R.id.status);
            comment= (TextView)itemView.findViewById(R.id.comment);
        }
    }
}
