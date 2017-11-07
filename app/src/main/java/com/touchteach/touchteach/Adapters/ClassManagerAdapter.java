package com.touchteach.touchteach.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.touchteach.touchteach.fragments.CMCreatedClassHolder;

/**
 * Created by sazgar on 11/7/2017.
 */

public class ClassManagerAdapter extends FragmentPagerAdapter {
    public ClassManagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //todo complete
        CMCreatedClassHolder result = new CMCreatedClassHolder();

        switch (position){
            case 0:
                result = new CMCreatedClassHolder();
                break;
            case 1:
//                result.getProgressBar().setVisibility(View.VISIBLE);
                break;
        }

        return result;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //todo resource all title to string resources
        switch (position){
            case 0:
                return "کلاس های ساخته شده";
            case 1:
                return "کلاس های شرکت کرده";
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
