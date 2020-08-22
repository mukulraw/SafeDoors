package com.technobrix.tbx.safedoors;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.TicketListPOJO.TicketList;
import com.technobrix.tbx.safedoors.TicketListPOJO.TicketListBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by tvs on 11/4/2017.
 */

public class MyBooking extends Fragment {

    RecyclerView grid;

    GridLayoutManager manager;

    BookingAdapter adapter;

    ProgressBar bar;

    List<TicketList> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mybooking , container , false);

        grid = (RecyclerView)view.findViewById(R.id.grid);

        bar = (ProgressBar) view.findViewById(R.id.progress);

        manager = new GridLayoutManager(getContext() , 1);

        list = new ArrayList<>();

        adapter = new BookingAdapter(getContext() , list);

        grid.setAdapter(adapter);

        grid.setLayoutManager(manager);

        bar.setVisibility(View.VISIBLE);









        Log.d("hglg" , "fghbjkdln");

        bean b = (bean)getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<TicketListBean> call = cr.ticketbean(b.socity  , b.userId );

        call.enqueue(new Callback<TicketListBean>() {
            @Override
            public void onResponse(Call<TicketListBean> call, Response<TicketListBean> response) {


                Log.d("dfgkjdf" , "response");

                adapter.setgrid(response.body().getTicketList());

                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<TicketListBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

                Log.d("bfdgjk" , t.toString());

            }
        });




        return view;
    }



    public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {

        Context context;

        List<TicketList> list = new ArrayList<>();


        public BookingAdapter(Context context ,  List<TicketList> list){

            this.context = context;
            this.list = list;

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(context).inflate(R.layout.mybooking_list_model , parent , false);
            return new MyViewHolder(view);

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            TicketList item = list.get(position);

            holder.ticket.setText(item.getTicketNo());
            holder.date.setText(item.getMeetingDate());
            holder.name.setText(item.getUserName());

        }


        public void setgrid( List<TicketList> list ){

            this.list = list;
            notifyDataSetChanged();

        }




        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView ticket  , date , name  ;

            public MyViewHolder(View itemView) {
                super(itemView);

                ticket = (TextView)itemView.findViewById(R.id.ticket);
               // time = (TextView)itemView.findViewById(R.id.time);
                date = (TextView)itemView.findViewById(R.id.date);
                name = (TextView)itemView.findViewById(R.id.name);
                //des = (TextView)itemView.findViewById(R.id.des);

            }
        }
    }


}

