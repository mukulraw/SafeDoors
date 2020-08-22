package com.technobrix.tbx.safedoors;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tvs on 11/17/2017.
 */

public class BookingTabs extends Fragment {

    TabLayout tabs;
    ViewPager pager;
    PagerAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bookingtabs , container , false);

        tabs = (TabLayout)view.findViewById(R.id.tabs);
        pager = (ViewPager)view.findViewById(R.id.pager);

        adapter = new PagerAdapter(getChildFragmentManager() , 2);

        tabs.addTab(tabs.newTab().setText("MY Tickets"));
        tabs.addTab(tabs.newTab().setText("My Bookings"));

        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);

        tabs.getTabAt(0).setText("MY Tickets");
        tabs.getTabAt(1).setText("My Bookings");

        return view;
    }
}
