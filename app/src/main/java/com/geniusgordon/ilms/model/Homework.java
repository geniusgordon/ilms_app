package com.geniusgordon.ilms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 9/27/15.
 */
public class Homework extends CourseItem implements Serializable {

    private String description;
    private List<Attachment> attachments;

    public Homework() {
        super();
        attachments = new ArrayList<Attachment>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }
}
