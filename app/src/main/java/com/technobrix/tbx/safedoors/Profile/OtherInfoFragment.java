package com.technobrix.tbx.safedoors.Profile;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.AddVehicle;
import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.GetVehiclePOJO.VehicleBean;
import com.technobrix.tbx.safedoors.GetVehiclePOJO.VehicleList;
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


public class OtherInfoFragment extends Fragment {


    RecyclerView recyclerView;
    GridLayoutManager manager;
    OtherAdapter adapter;
    List<VehicleList> list;
    ProgressBar bar;
    TextView add;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.other_info_fragment , container , false);
        recyclerView = (RecyclerView)view.findViewById(R.id.other);

        bar = (ProgressBar)view.findViewById(R.id.bar);

        add = (TextView)view.findViewById(R.id.add);
        manager= new GridLayoutManager(getContext(),1);

        list = new ArrayList<>();
        adapter = new OtherAdapter(getContext() , list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext() , AddVehicle.class);
                startActivity(i);
            }
        });


        bar.setVisibility(View.VISIBLE);
        bean b = (bean)getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<VehicleBean> call = cr.vehiclebean(b.userId);

        call.enqueue(new Callback<VehicleBean>() {
            @Override
            public void onResponse(Call<VehicleBean> call, Response<VehicleBean> response) {


                adapter.setgrid(response.body().getVehicleList());
                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<VehicleBean> call, Throwable t) {


                bar.setVisibility(View.GONE);
            }


        });
        return view;
    }
}
