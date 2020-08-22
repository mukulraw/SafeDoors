package com.technobrix.tbx.safedoors.NoticeBoard;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.NoticeListPOJO.NoticeList;
import com.technobrix.tbx.safedoors.R;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {

    Context context;
    List<NoticeList> list = new ArrayList<>();

    public NoticeAdapter(Context context , List<NoticeList> list){

        this.context = context;
        this.list = list;
    }

    @Override
    public NoticeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.notice_board_list_model , parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoticeAdapter.MyViewHolder holder, int position) {

        final NoticeList item = list.get(position);
        holder.title.setText(item.getTitle());
        holder.date.setText(item.getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager =((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                NoticeBoard2 fragment = new NoticeBoard2();
                Bundle b = new Bundle();
                b.putString("title", item.getTitle());
                b.putString("date", item.getDate());
                b.putString("description", item.getDescription());

                fragment.setArguments(b);
                ft.replace(R.id.replace, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();





            }
        });


    }

    public void setGridData(List<NoticeList> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title , date , des;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.title);
            date = (TextView)itemView.findViewById(R.id.date);
            des = (TextView)itemView.findViewById(R.id.notice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                }
            });
        }
    }
}
