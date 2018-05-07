package com.company.antoine.moodtracker.Controllers.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.company.antoine.moodtracker.Controllers.Fragments.PageViewPager;

/**
 * The PageAdapter allows you to define Viewpager settings.
 */
public class PageAdapter extends FragmentPagerAdapter{

    private int[] mColors;

    //Constructor of PageAdapter.
    PageAdapter(FragmentManager pFPM, int[] pColors) {
        super(pFPM);
        mColors = pColors;
    }

    //Set the page count of the viewPager.
    public int getCount(){
        return (5);
    }

    //This method is called whenever a request page is loaded.
    public Fragment getItem(int pPosition) {
        return(PageViewPager.newInstance(pPosition, mColors[pPosition]));
    }
}
