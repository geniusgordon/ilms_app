package com.example.gordon.ilms.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 9/30/15.
 */
public class ReplyList {
    private String title;
    private List<Reply> replies;

    public ReplyList() {
        replies = new ArrayList<Reply>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
}
