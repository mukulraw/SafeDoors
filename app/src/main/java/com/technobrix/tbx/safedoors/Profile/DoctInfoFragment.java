package com.technobrix.tbx.safedoors.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technobrix.tbx.safedoors.R;

import java.util.ArrayList;
import java.util.List;


public class DoctInfoFragment extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    DoctAdapter adapter;
    List<String> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.docinfo , container , false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        manager = new GridLayoutManager(getContext() , 1);
        list = new ArrayList();
        adapter = new DoctAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        return view;
    }
}
