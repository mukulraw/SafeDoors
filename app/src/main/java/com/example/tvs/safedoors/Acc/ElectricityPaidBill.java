package com.example.tvs.safedoors.Acc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tvs.safedoors.MainActivity;
import com.example.tvs.safedoors.R;

public class ElectricityPaidBill extends Fragment {

    TextView paid,unpaid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.electricity_paid_baill , container , false);

        paid = (TextView)view.findViewById(R.id.paid);
        unpaid = (TextView)view.findViewById(R.id.unpaid);
        paid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =((MainActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                BillType fragment = new BillType();
                ft.replace(R.id.replace, fragment);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();

            }
        });
        return view;
    }
}
