package com.technobrix.tbx.safedoors.Event;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.BuyTicket;
import com.technobrix.tbx.safedoors.EventBookPOJO.EventBookBean;
import com.technobrix.tbx.safedoors.EventDatePOJO.MeetingList;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {


    Context context;

    List<MeetingList> list = new ArrayList<>();

    public EventAdapter(Context context ,  List<MeetingList> list){

        this.context = context;

        this .list = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.event_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        final MeetingList item = list.get(position);

        String daa = item.getMeetingDate();
        String[] d1 = daa.split("-");

        holder.d.setText(d1[2]);
       holder. m.setText(d1[1] + " " + d1[0]);

       holder. title.setText(item.getTitle());
       holder.desc.setText(item.getDescription());






       holder.buyticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                bean b = (bean)context.getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://safedoors.in")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                Call<EventBookBean> call = cr.eventbook(b.socity_id , b.house_id , b.userId , item.getId() , item.getEventPrice());

                call.enqueue(new Callback<EventBookBean>() {
                    @Override
                    public void onResponse(Call<EventBookBean> call, Response<EventBookBean> response) {

                       Intent i = new Intent(context , BuyTicket.class);

                       i.putExtra("title" , item.getTitle());
                       context.startActivity(i);

                    }

                    @Override
                    public void onFailure(Call<EventBookBean> call, Throwable t) {

                    }
                });


            }
        });

    }

    public void Setgrid(List<MeetingList> list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()  {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView d , m , title , desc;

        Button paid , buyticket;

        public MyViewHolder(View itemView) {
            super(itemView);

            d = (TextView)itemView.findViewById(R.id.day);
            m = (TextView)itemView.findViewById(R.id.month);
            title = (TextView)itemView.findViewById(R.id.title);
            desc = (TextView)itemView.findViewById(R.id.description);

            paid = (Button)itemView.findViewById(R.id.paid);
            buyticket = (Button)itemView.findViewById(R.id.butticket);

        }
    }
}
