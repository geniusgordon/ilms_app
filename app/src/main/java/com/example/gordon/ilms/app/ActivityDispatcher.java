package com.example.gordon.ilms.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.gordon.ilms.model.Course;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * Created by gordon on 9/30/15.
 */
public class ActivityDispatcher extends Activity {
    final static String LOG_TAG = "ActivityDispatcher";

    private static Class[] activitiesToOpen = {
            MainActivity.class,
            CourseActivity.class,
            DetailActivity.class,
            ForumActivity.class,
            PostDetailActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri uri = getIntent().getData();
        Log.d(LOG_TAG, uri.toString());

        for (Class activityClass: activitiesToOpen) {
            try {
                Object obj = activityClass.newInstance();
                Intent intent = (Intent) activityClass.getMethod("isIntentUri",
                        Uri.class, ActivityDispatcher.this.getClass())
                        .invoke(obj, uri, ActivityDispatcher.this);
                if (intent != null) {
                    finish();
                    startActivity(intent);
                    break;
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
