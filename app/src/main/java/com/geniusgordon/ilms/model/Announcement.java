package com.geniusgordon.ilms.model;

import java.io.Serializable;

/**
 * Created by gordon on 9/27/15.
 */
public class Announcement extends CourseItem implements Serializable {

    private String content;
    private long popularity;
    private String attachment;

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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
