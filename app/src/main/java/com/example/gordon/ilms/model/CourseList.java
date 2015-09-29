package com.example.gordon.ilms.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 9/28/15.
 */
public class CourseList {
    private String semester;
    private List<Course> courses;

    public CourseList() {
        courses = new ArrayList<Course>();
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String toJsonString() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("semester", semester);
        JSONArray array = new JSONArray();
        for (Course course: courses)
            array.put(new JSONObject(course.toJsonString()));
        object.put("courses", array);
        return object.toString();
    }

    public static CourseList fromJson(String jsonStr) throws JSONException {
        CourseList courseList = new CourseList();
        JSONObject courseListJson = new JSONObject(jsonStr);
        courseList.semester = courseListJson.getString("semester");
        JSONArray array = courseListJson.getJSONArray("courses");
        for (int i = 0; i < array.length(); i++)
            courseList.courses.add(Course.fromJson(array.getString(i)));
        return courseList;
    }
}
