package com.company.antoine.moodtracker.Controllers.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.company.antoine.moodtracker.Controllers.Fragments.PageFragment;

public class PageAdapter extends FragmentPagerAdapter{

    private int[] mColors;

    PageAdapter(FragmentManager pFPM, int[] pColors) {
        super(pFPM);
        mColors = pColors;
    }

    public int getCount(){
        return (5);
    }

    public Fragment getItem(int position) {
        // 4 - Page to return
        return(PageFragment.newInstance(position, this.mColors[position]));
    }
}
