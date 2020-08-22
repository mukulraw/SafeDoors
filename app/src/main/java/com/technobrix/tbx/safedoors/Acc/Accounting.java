package com.technobrix.tbx.safedoors.Acc;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.technobrix.tbx.safedoors.MainActivity;
import com.technobrix.tbx.safedoors.R;


public class Accounting extends Fragment {

    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.accounting_layout , container , false);

        linearLayout1 = (LinearLayout)view.findViewById(R.id.linear1);
        linearLayout2 = (LinearLayout)view.findViewById(R.id.linear2);
        linearLayout3 = (LinearLayout)view.findViewById(R.id.linear3);
        linearLayout4 = (LinearLayout)view.findViewById(R.id.linear4);
        linearLayout5 = (LinearLayout)view.findViewById(R.id.linear5);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =((MainActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ElectricityBill fragment = new ElectricityBill();
                ft.replace(R.id.replace, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();

            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fragmentManager =((MainActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ElectricityBill fragment = new ElectricityBill();
                ft.replace(R.id.replace, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();

            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =((MainActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ElectricityBill fragment = new ElectricityBill();
                ft.replace(R.id.replace, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();

            }
        });

        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =((MainActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ElectricityBill fragment = new ElectricityBill();
                ft.replace(R.id.replace, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();

            }
        });
        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =((MainActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ElectricityBill fragment = new ElectricityBill();
                ft.replace(R.id.replace, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();

            }
        });
        return view;

    }
}
