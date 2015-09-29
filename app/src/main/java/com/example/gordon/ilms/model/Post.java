package com.example.gordon.ilms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by gordon on 9/29/15.
 */
public class Post implements Serializable {
    private long id;
    private String title;
    private String lastName;
    private Date lastTime;
    private long count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
