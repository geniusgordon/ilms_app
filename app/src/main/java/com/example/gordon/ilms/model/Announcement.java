package com.example.gordon.ilms.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gordon on 9/27/15.
 */
public class Announcement implements Serializable {

    private long id;
    private String title;
    private Date time;
    private String content;
    private long popularity;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public long getPopularity() {
        return popularity;
    }

    public void setPopularity(long popularity) {
        this.popularity = popularity;
    }

}
