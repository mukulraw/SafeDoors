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

import com.technobrix.tbx.safedoors.Add;
import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.GetFamilyPOJO.FamiltList;
import com.technobrix.tbx.safedoors.GetFamilyPOJO.GetFamilyBean;
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


public class FamilyInfoFragment extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    familyAdapter adapter;
    List<FamiltList> list;
    ProgressBar bar;
    TextView add;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.family_info , container, false);

        recyclerView = (RecyclerView)v.findViewById(R.id.family);

        add = (TextView)v.findViewById(R.id.add);

        bar = (ProgressBar) v.findViewById(R.id.bar);

        manager= new GridLayoutManager(getContext(),1);

        adapter = new familyAdapter(getContext(), list);

        list = new ArrayList<>();

        adapter = new familyAdapter(getContext(),list);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);

        bar.setVisibility(View.VISIBLE);

        bean b = (bean)getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<GetFamilyBean> call = cr.family(b.userId);

        call.enqueue(new Callback<GetFamilyBean>() {
            @Override
            public void onResponse(Call<GetFamilyBean> call, Response<GetFamilyBean> response) {

               adapter.Setgriddata(response.body().getFamiltList());

               bar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GetFamilyBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), Add.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
