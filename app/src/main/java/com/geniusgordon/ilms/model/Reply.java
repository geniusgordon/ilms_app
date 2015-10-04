package com.geniusgordon.ilms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gordon on 9/29/15.
 */
public class Reply {
    private long id;
    private long floor;
    private String account;
    private String name;
    private String email;
    private String content;
    private Date time;
    private List<Attachment> attachments;

    public Reply() {
        attachments = new ArrayList<Attachment>();
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getFloor() {
        return floor;
    }

    public void setFloor(long floor) {
        this.floor = floor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }
}
