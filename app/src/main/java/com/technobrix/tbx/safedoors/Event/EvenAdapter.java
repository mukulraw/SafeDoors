package com.technobrix.tbx.safedoors.Event;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by tvs on 10/31/2017.
 */

public class EvenAdapter extends FragmentStatePagerAdapter {


    public EvenAdapter(FragmentManager fm , int tab) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0){
            return new Calender();
        }
        else if (position == 1){

            return new Meeting();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
