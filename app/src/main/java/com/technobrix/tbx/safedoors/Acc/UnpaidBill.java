package com.technobrix.tbx.safedoors.Acc;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technobrix.tbx.safedoors.R;



public class UnpaidBill extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    UnpaidAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.unpaid_bill, container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.unpaid);
        adapter = new UnpaidAdapter(getContext());
        manager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
