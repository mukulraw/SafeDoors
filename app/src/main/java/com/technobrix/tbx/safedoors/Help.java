package com.technobrix.tbx.safedoors;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Help extends Fragment{

    TabLayout tabs;
    ViewPager pager;
    ViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.help , container , false);

        tabs = (TabLayout)view.findViewById(R.id.tabs);
        pager = (ViewPager)view.findViewById(R.id.pager);

        adapter = new ViewAdapter(getChildFragmentManager() , 2);

        tabs.addTab(tabs.newTab().setText("Society"));
        tabs.addTab(tabs.newTab().setText("Emergency"));

        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);

        tabs.getTabAt(0).setText("Society");
        tabs.getTabAt(1).setText("Emergency");

        return view;
    }


    public class ViewAdapter extends FragmentStatePagerAdapter {


        public ViewAdapter(FragmentManager fm , int tab) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {


            if (position == 0){

                return new Society();
            }

            else if (position == 1){

                return new Emergency();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }




}

