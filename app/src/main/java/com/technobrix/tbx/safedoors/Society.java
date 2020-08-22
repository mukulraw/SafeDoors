package com.technobrix.tbx.safedoors;

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

import com.technobrix.tbx.safedoors.GetHelpPOJO.HelpBean;
import com.technobrix.tbx.safedoors.GetHelpPOJO.HelpList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by tvs on 10/16/2017.
 */

public class Society extends Fragment {


    RecyclerView recyclerView;
    GridLayoutManager manager;
    SocietyAdapter adapter;
    ProgressBar bar;
    List<HelpList> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.society , container , false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);

        bar = (ProgressBar) view.findViewById(R.id.progress);

        manager = new GridLayoutManager(getContext() , 1);

        list = new ArrayList<>();

        adapter = new SocietyAdapter( getActivity() , list);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);

        bar.setVisibility(View.VISIBLE);

        bean b = (bean)getContext().getApplicationContext();

        Log.d("fhsdg" , "gjdsfa");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        //Call<HelpBean> call = cr.help("1");
        Call<HelpBean> call = cr.help(b.socity);

        call.enqueue(new Callback<HelpBean>() {
            @Override
            public void onResponse(Call<HelpBean> call, Response<HelpBean> response) {

                adapter.setgrid(response.body().getHelpList());

                bar.setVisibility(View.GONE);

                Log.d("kamal"  , "response");
            }

            @Override
            public void onFailure(Call<HelpBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

                Log.d("gfg" , t.toString());

            }
        });

        return view;
    }
}
