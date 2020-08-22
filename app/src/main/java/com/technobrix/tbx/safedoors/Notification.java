package com.technobrix.tbx.safedoors;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;



import com.technobrix.tbx.safedoors.NotificationListPOJO.NotifiBean;
import com.technobrix.tbx.safedoors.NotificationListPOJO.NotificationList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Notification extends AppCompatActivity  {

    RecyclerView recyclerView;

    GridLayoutManager manager;

    NotiAdapter adapter;

    List<NotificationList> list;

    ProgressBar bar;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIcon(R.drawable.arrowleft);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        manager = new GridLayoutManager(getApplication() , 1);

        list = new ArrayList<>();
        adapter = new NotiAdapter(this  , list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        bar = (ProgressBar) findViewById(R.id.progress);

        bar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bean b = (bean)getApplicationContext();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);
        Call<NotifiBean> call = cr.notify("1", "1" );

        call.enqueue(new Callback<NotifiBean>() {
            @Override
            public void onResponse(Call<NotifiBean> call, Response<NotifiBean> response) {

                adapter.Setgrid(response.body().getNotificationList());

                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<NotifiBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle("NOTIFICATION");

    }
}
