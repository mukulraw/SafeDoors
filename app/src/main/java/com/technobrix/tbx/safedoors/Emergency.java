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

import java.util.ArrayList;
import java.util.List;


public class Emergency extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    ProgressBar bar;
    EmerAdapter adapter;

    List<EmerBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.emer , container , false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);

       // bar = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getContext() , 1);

        list = new ArrayList<>();

        EmerBean b1 = new EmerBean();
        b1.setTitle("Police");
        b1.setNumber("100");
        list.add(b1);

        EmerBean b2 = new EmerBean();
        b2.setTitle("Ambulance ");
        b2.setNumber("102");
        list.add(b2);


        EmerBean b3 = new EmerBean();
        b3.setTitle("Blood Requirement");
        b3.setNumber("104");
        list.add(b3);


        EmerBean b4 = new EmerBean();
        b4.setTitle("Child Helpline ");
        b4.setNumber("1098");
        list.add(b4);


        EmerBean b5 = new EmerBean();
        b5.setTitle("phones ");
        b5.setNumber("112");
        list.add(b5);

        EmerBean b6 = new EmerBean();
        b6.setTitle("Helpline");
        b6.setNumber("1363");
        list.add(b6);

        adapter = new EmerAdapter(getActivity() , list);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        Log.d("hbfdsg" , "man");

        return view;
    }
}
