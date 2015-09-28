package com.example.gordon.ilms.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gordon.ilms.R;
import com.example.gordon.ilms.model.Course;

/**
 * Created by gordon on 9/26/15.
 */
public class CoursePageFragment extends Fragment {
    final static String LOG_TAG = "CoursePageFragment";
    protected Course course;

    public CoursePageFragment(Course course) {
        super();
        this.course = course;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "on create view");
        return inflater.inflate(R.layout.test, container, false);
    }
}
