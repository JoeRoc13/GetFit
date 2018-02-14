package com.example.atouf.workoutapplication;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by atouf on 11/15/2017.
 */

public class CategoryAdapter extends FragmentPagerAdapter {
    private Context wContext;

    public CategoryAdapter(Context context, FragmentManager fm){
        super(fm);
        wContext = context;
    }

    @Override
    public Fragment getItem(int position){
        if(position == 0) {
            return new MondayFragment();
        }else if (position ==1 ){
            return new TuesdayFragment();
        }else if (position == 2){
            return new WednesdayFragment();
        }else if (position == 3) {
            return new ThursdayFragment();
        } else {
            return new FridayFragment();
        }
    }

    @Override
    public int getCount() {return 5;}

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return "Monday";
        }else if (position ==1 ){
            return "Tuesday";
        }else if (position == 2){
            return "Wednesday";
        }else if (position == 3) {
            return "Thursday";
        } else {
            return "Friday";
        }
    }
}
