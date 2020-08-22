package com.technobrix.tbx.safedoors.NewGatekeeper;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.R;

/**
 * Created by tvs on 10/26/2017.
 */

public class RegularAdapter extends RecyclerView.Adapter<RegularAdapter.MyViewHolder> {

    Context context;


    public  RegularAdapter (Context context){
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.regular_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView time , visitor;

        public MyViewHolder(View itemView) {
            super(itemView);


            time = (TextView)itemView.findViewById(R.id.time);
            visitor = (TextView)itemView.findViewById(R.id.visitor);
        }
    }
}
