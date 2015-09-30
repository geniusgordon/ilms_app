package com.example.gordon.ilms.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.gordon.ilms.model.Course;

/**
 * Created by gordon on 9/30/15.
 */
public class ActivityDispatcher extends Activity {
    final static String LOG_TAG = "ActivityDispatcher";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri uri = getIntent().getData();
        Log.d(LOG_TAG, uri.toString());
        long courseId = Long.parseLong(uri.getQueryParameter("courseID"));
        Course course = new Course();
        course.setId(courseId);
        course.setChi_title("");
        course.setEng_title("");
        Intent intent = new Intent(this, CourseActivity.class);
        intent.putExtra("course", course);
        finish();
        startActivity(intent);
    }
}
