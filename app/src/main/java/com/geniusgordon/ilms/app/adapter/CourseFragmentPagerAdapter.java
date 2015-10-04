package com.geniusgordon.ilms.app.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.geniusgordon.ilms.app.fragment.AnnouncementFragment;
import com.geniusgordon.ilms.app.fragment.HomeworkFragment;
import com.geniusgordon.ilms.app.fragment.MaterialFragment;
import com.geniusgordon.ilms.model.Course;

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
        fragments[0] = new AnnouncementFragment();
        fragments[1] = new MaterialFragment();
        fragments[2] = new HomeworkFragment();

        for (int i = 0; i < fragments.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("course", course);
            fragments[i].setArguments(bundle);
        }
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
