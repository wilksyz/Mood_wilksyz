package com.company.antoine.moodtracker.Controllers.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.company.antoine.moodtracker.Controllers.Fragments.PageFragment;


public class PageAdapter extends FragmentPagerAdapter{

    private int[] mColors;
    private int i = 0;


    PageAdapter(FragmentManager pFPM, int[] pColors) {
        super(pFPM);
        mColors = pColors;
    }

    public int getCount(){
        return (5);
    }

    public Fragment getItem(int pPosition) {
        return(PageFragment.newInstance(pPosition, this.mColors[pPosition]));
    }


}
