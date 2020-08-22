package com.technobrix.tbx.safedoors.Event;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.GetAllMeetingPOJO.GetAllBean;
import com.technobrix.tbx.safedoors.GetAllMeetingPOJO.MeetingList;
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

/**
 * Created by tvs on 10/31/2017.
 */

public class Meeting extends Fragment {

    RecyclerView grid;

    GridLayoutManager manager;

    MeetingAdapter adapter;

    List<MeetingList> list;

    ProgressBar bar;

    FloatingActionButton filter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.event_meeting , container , false);

        grid = (RecyclerView)view.findViewById(R.id.grid);

        bar = (ProgressBar)view.findViewById(R.id.progress);

        filter = (FloatingActionButton)view.findViewById(R.id.filter);

        manager = new GridLayoutManager(getContext() , 1);

        list = new ArrayList<>();

        adapter = new MeetingAdapter(getContext(), list);

        grid.setLayoutManager(manager);

        grid.setAdapter(adapter);


        bar.setVisibility(View.VISIBLE);

        Log.d("dfhisd" , "to");

        bean b = (bean)getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<GetAllBean> call = cr.me(b.socity);

        call.enqueue(new Callback<GetAllBean>() {
            @Override
            public void onResponse(Call<GetAllBean> call, Response<GetAllBean> response) {

                Log.d("hfgshdf" , "response");

                list = response.body().getMeetingList();

                adapter.setgrid(list);

                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<GetAllBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

                Log.d("shdfgkjdf" , t.toString());
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.filter_popup);
                dialog.setCancelable(true);
                dialog.show();


                TextView filter = (TextView)dialog.findViewById(R.id.filter);
                final DatePicker picker = (DatePicker)dialog.findViewById(R.id.picker);
                Button clear = (Button)dialog.findViewById(R.id.clear);
                Button ok = (Button)dialog.findViewById(R.id.ok);



                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

adapter.setgrid(list);
dialog.dismiss();




                    }
                });


                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String day = String.valueOf(picker.getDayOfMonth());
                        String month = String.valueOf(picker.getMonth() + 1);
                        String year = String.valueOf(picker.getYear());

                       final String date = year + "-" + month + "-" + day;

                       bar.setVisibility(View.VISIBLE);

                       Log.d("vkfdlg" , "hlo");

                       bean b = (bean)getContext().getApplicationContext();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://safedoors.in")
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllApiInterface cr = retrofit.create(AllApiInterface.class);

                        Call<GetAllBean> call = cr.mdb(b.socity , date);

                        Log.d("hgdfg" ,date);

                       call.enqueue(new Callback<GetAllBean>() {
                           @Override
                           public void onResponse(Call<GetAllBean> call, Response<GetAllBean> response) {

                               Log.d("bsgkflg" , "response");

                               adapter.setgrid(response.body().getMeetingList());

                               bar.setVisibility(View.GONE);

                               dialog.dismiss();

                           }

                           @Override
                           public void onFailure(Call<GetAllBean> call, Throwable t) {

                               bar.setVisibility(View.GONE);

                               Log.d("jfdsgjkd" , t.toString());

                           }
                       });

                    }
                });


            }
        });

        return view;

    }
}
