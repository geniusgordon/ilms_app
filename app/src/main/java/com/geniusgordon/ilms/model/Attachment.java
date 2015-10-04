package com.geniusgordon.ilms.model;

/**
 * Created by gordon on 9/27/15.
 */
public class Attachment {
    final static String URL = "http://lms.nthu.edu.tw/sys/read_attach.php?id=%s";

    private String title;
    private long id;
    private String size;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setSize(long size) {
        if (size >= 1000000000) {
            this.size = String.format("(%s GB)", size/1000000000);
        } else if (size >= 1000000) {
            this.size = String.format("(%s MB)", size/1000000);
        } else if (size >= 1000) {
            this.size = String.format("(%s KB)", size/1000);
        } else {
            this.size = String.format("(%s B)", size);
        }
    }

    public String getUrl() {
        return String.format(URL, id);
    }
}
