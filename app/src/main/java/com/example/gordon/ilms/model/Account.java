package com.example.gordon.ilms.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by gordon on 9/28/15.
 */
public class Account implements Serializable {
    private String name;
    private String studentId;
    private String email;
    private String avatarUrl;
    private String lastLogin;
    private String loginCount;

    public Account() {
        name = "";
        studentId = "";
        email = "";
        avatarUrl = "";
        lastLogin = "";
        loginCount = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(String loginCount) {
        this.loginCount = loginCount;
    }

    public String toJsonString() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("studentId", studentId);
        object.put("email", email);
        object.put("avatarUrl", avatarUrl);
        object.put("lastLogin", lastLogin);
        object.put("loginCount", loginCount);
        return object.toString();
    }

    public static Account fromJson(String jsonStr) throws JSONException {
        Account account = new Account();
        JSONObject accountJson = new JSONObject(jsonStr);
        account.name = accountJson.getString("name");
        account.studentId = accountJson.getString("studentId");
        account.email = accountJson.getString("email");
        account.avatarUrl = accountJson.getString("avatarUrl");
        account.lastLogin = accountJson.getString("lastLogin");
        account.loginCount = accountJson.getString("loginCount");
        return account;
    }
}
