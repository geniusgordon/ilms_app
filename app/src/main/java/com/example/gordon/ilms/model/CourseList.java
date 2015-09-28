package com.example.gordon.ilms.model;

import java.util.List;

/**
 * Created by gordon on 9/28/15.
 */
public class CourseList {
    private String semester;
    private List<Course> courses;

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
}
