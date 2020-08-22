package com.technobrix.tbx.safedoors.Acc;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.MainActivity;
import com.technobrix.tbx.safedoors.R;

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
