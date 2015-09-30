package com.example.gordon.ilms.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gordon on 9/27/15.
 */
public class Announcement extends CourseItem implements Serializable {

    private String content;
    private long popularity;

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
