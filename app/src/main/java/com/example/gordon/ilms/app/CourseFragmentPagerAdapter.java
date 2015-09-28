package com.example.gordon.ilms.app;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by gordon on 9/26/15.
 */
public class CourseFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    final String[] title = {"公告", " 教材", "作業"};

    public CourseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new CoursePageFragment();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
