package com.example.gordon.ilms.app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;

import com.example.gordon.ilms.R;
import com.example.gordon.ilms.model.Course;

public class CourseActivity extends DrawerActivity {
    final static String LOG_TAG = "CourseActivity";


    private Course course;

    private CourseFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        initDrawer();

        course = (Course) getIntent().getSerializableExtra("course");
        Log.d(LOG_TAG, course.getChi_title());

        getSupportActionBar().setTitle(course.getChi_title());

        pagerAdapter = new CourseFragmentPagerAdapter(getSupportFragmentManager(), course);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        toolbar.inflateMenu(R.menu.menu_course);
        return true;
    }

}
