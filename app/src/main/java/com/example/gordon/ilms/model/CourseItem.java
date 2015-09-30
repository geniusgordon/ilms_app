package com.example.gordon.ilms.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by gordon on 9/30/15.
 */
public class CourseItem implements Serializable {
    protected long id;
    protected String title;
    protected Date time;

    public CourseItem() {
        title = "";
    }

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

    public String getTimeStr(DateFormat df) {
        if (time == null)
            return "";
        return df.format(time);
    }
}
