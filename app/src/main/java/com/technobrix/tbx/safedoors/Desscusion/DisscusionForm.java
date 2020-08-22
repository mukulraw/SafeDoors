package com.technobrix.tbx.safedoors.Desscusion;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.TopicListPOJO.TopicBean;
import com.technobrix.tbx.safedoors.TopicListPOJO.TopicList;
import com.technobrix.tbx.safedoors.bean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class DisscusionForm extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    HelpDiskAdapeter adapeter;
    FloatingActionButton plus;
    ProgressBar bar;
    List<TopicList>list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.helpdesk , container ,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.helpdesk);

        plus = (FloatingActionButton)view.findViewById(R.id.plus) ;
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext() , Add.class);
                startActivity(i);
            }
        });

        bar = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getContext(),1);

        list = new ArrayList<>();

        adapeter = new HelpDiskAdapeter(getContext() , list);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapeter);





        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();

        bar.setVisibility(View.VISIBLE);

        bean b = (bean)getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<TopicBean> call = cr.topic(b.socity , b.userId);

        call.enqueue(new Callback<TopicBean>() {
            @Override
            public void onResponse(Call<TopicBean> call, Response<TopicBean> response) {

                adapeter.setgrid(response.body().getTopicList());

                bar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<TopicBean> call, Throwable t) {

                bar.setVisibility(View.GONE);
            }
        });
    }
}
