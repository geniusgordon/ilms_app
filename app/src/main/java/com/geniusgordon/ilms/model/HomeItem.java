package com.geniusgordon.ilms.model;

import java.util.Date;

/**
 * Created by gordon on 9/29/15.
 */
public class HomeItem {
    public final static int ANNOUNCE = 1;
    public final static int FORUM = 2;
    public final static int MATERIAL = 3;

    private boolean isHeader;
    private String title;
    private String courseName;
    private String url;
    private Date date;
    private int type;

    public HomeItem(String title) {
        this.isHeader = true;
        this.title = title;
    }

    public HomeItem(int type, String title, String courseName, String url, Date date) {
        this.isHeader = false;
        this.title = title;
        this.courseName = courseName;
        this.url = url;
        this.date = date;
        this.type = type;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getType() {
        return this.type;
    }
}
