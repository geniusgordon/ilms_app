package com.example.gordon.ilms.app;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.gordon.ilms.model.Course;

/**
 * Created by gordon on 9/26/15.
 */
public class CourseFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private final String[] title = {"公告", " 教材", "作業"};
    private Fragment[] fragments;
    private Course course;

    public CourseFragmentPagerAdapter(FragmentManager fm, Course course) {
        super(fm);
        this.course = course;
        fragments = new Fragment[PAGE_COUNT];
        fragments[0] = new AnnouncementFragment(course);
        fragments[1] = new CoursePageFragment(course);
        fragments[2] = new CoursePageFragment(course);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
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
