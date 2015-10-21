package com.geniusgordon.ilms.model;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by gordon on 10/19/15.
 */
public class Event {
    public static int EVENT = 1;
    public static int HOMEWORK = 2;
    public static int TEST = 3;

    Date date;
    String title;
    int type;
    Course course;
    String url;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateStr(DateFormat df) {
        return (date == null) ? "" : df.format(date);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
