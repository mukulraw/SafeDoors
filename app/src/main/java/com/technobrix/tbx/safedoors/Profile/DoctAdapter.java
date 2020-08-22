package com.technobrix.tbx.safedoors.Profile;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.DocumentListPOJO.DocumentList;
import com.technobrix.tbx.safedoors.R;

import java.util.ArrayList;
import java.util.List;


public class DoctAdapter extends RecyclerView.Adapter<DoctAdapter.MyViewHolder> {

    Context context;
    List<DocumentList>list = new ArrayList();

    public DoctAdapter(Context context ,  List<DocumentList>list){

        this.context = context;
        this.list = list;
    }


    @Override
    public DoctAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.doc_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DoctAdapter.MyViewHolder holder, int position) {

        DocumentList item = list.get(position);

        holder.aadhar.setText(item.getTitle());



    }
    public void set( List<DocumentList>list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView aadhar;


        public MyViewHolder(View itemView) {
            super(itemView);

            aadhar = (TextView)itemView.findViewById(R.id.aadhar);
        }
    }
}
