package com.example.gordon.ilms.model;

import java.io.Serializable;

/**
 * Created by gordon on 9/28/15.
 */
public class Account implements Serializable {
    private String studentId;
    private String email;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
