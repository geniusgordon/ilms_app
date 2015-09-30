package com.example.gordon.ilms.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

/**
 * Created by gordon on 9/28/15.
 */
public class Preferences {
    final static String ILMS = "ilms";
    final static String STUDENT_ID = "student_id";
    final static String EMAIL = "email";
    final static String COOKIE = "cookie";
    final static String COURSE_LIST = "course_list";

    private static Preferences mInstance;
    private static Context mContext;
    private SharedPreferences settings;

    private Preferences(Context context) {
        mContext = context;
        settings = context.getSharedPreferences(ILMS, 0);
    }

    public static synchronized Preferences getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Preferences(context.getApplicationContext());
        }
        return mInstance;
    }

    public static synchronized Preferences getInstance() {
        return mInstance;
    }

    public void saveAccount(Account account) {
        settings.edit()
                .putString(STUDENT_ID, account.getStudentId())
                .putString(EMAIL, account.getEmail())
                .apply();
        Log.d("Preferences", "save account " + account.getStudentId());
    }

    public Account getAccount() {
        Account account = new Account();
        account.setStudentId(settings.getString(STUDENT_ID, ""));
        account.setEmail(settings.getString(EMAIL, ""));
        if (account.getStudentId().equals("") || account.getEmail().equals("")) {
            Log.d("Preferences", "get account null");
            return null;
        }
        Log.d("Preferences", "get account " + account.getStudentId());
        return account;
    }

    public void saveCookie(String cookie) {
        if (cookie == null)
            return;
        settings.edit()
                .putString(COOKIE, cookie)
                .apply();
    }

    public String getCookie() {
        String cookie = settings.getString(COOKIE, "");
        return cookie;
    }

    public void logout() {
        settings.edit()
                .remove(STUDENT_ID)
                .remove(EMAIL)
                .remove(COOKIE)
                .remove(COURSE_LIST)
                .apply();
    }

    public void saveCourseList(CourseList courseList) {
        try {
            String courseListStr = courseList.toJsonString();
            settings.edit()
                    .putString(COURSE_LIST, courseListStr)
                    .apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public CourseList getCourseList() {
        String courseListStr = settings.getString(COURSE_LIST, "");
        if (courseListStr.equals(""))
            return null;
        try {
            return CourseList.fromJson(courseListStr);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public long getCourseIdByName(String courseName) {
        CourseList courseList = getCourseList();
        for (Course course: courseList.getCourses()) {
            if (course.getChi_title().equals(courseName))
                return course.getId();
            if (course.getEng_title().equals(courseName))
                return course.getId();
        }
        return 0;
    }
}
