package com.technobrix.tbx.safedoors.Profile;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewAdapter extends FragmentStatePagerAdapter {

    int tabs;

    public ViewAdapter(FragmentManager fm, int List) {
        super(fm);
        this.tabs = List;
    }

    @Override
    public Fragment getItem(int position) {


        if (position == 0)
        {
            return new ProfileInfoFragment();
        }
        else if (position == 1)
        {
            return new FamilyInfoFragment();
        }
        else if (position == 2)
        {
            return new DoctInfoFragment();
        }
        else if (position == 3)
        {
            return new OtherInfoFragment();
        }
        return null;
    }

    @Override
    public int getCount () {
        return 4;
    }
}
