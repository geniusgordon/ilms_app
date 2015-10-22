package com.geniusgordon.ilms.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;

/**
 * Created by gordon on 9/28/15.
 */
public class Preferences {
    final static String ILMS = "ilms";
    final static String ACCOUNT = "account";
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
        String accountStr = "";
        try {
            accountStr = account.toJsonString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        settings.edit()
                .putString(ACCOUNT, accountStr)
                .apply();
        Log.d("Preferences", "save account " + account.getStudentId());
    }

    public Account getAccount() {
        String accountStr = settings.getString(ACCOUNT, "");
        Log.d("Prefs getAccount", accountStr==null?"":accountStr);
        try {
            return Account.fromJson(accountStr);
        } catch (JSONException e) {
            //e.printStackTrace();
            return null;
        }
    }

    public void saveCookie(String cookie) {
        if (cookie == null)
            return;
        settings.edit()
                .putString(COOKIE, cookie)
                .apply();
    }

    public String getCookie() {
//        Log.d("Cookie", settings.getString(COOKIE, ""));
        return settings.getString(COOKIE, "");
    }

    public void logout() {
        settings.edit()
                .remove(ACCOUNT)
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
