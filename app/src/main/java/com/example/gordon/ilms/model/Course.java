package com.example.gordon.ilms.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by gordon on 9/27/15.
 */
public class Course implements Serializable {

    private long id;
    private String chi_title;
    private String eng_title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChi_title() {
        return chi_title;
    }

    public void setChi_title(String chi_title) {
        this.chi_title = chi_title;
    }

    public String getEng_title() {
        return eng_title;
    }

    public void setEng_title(String eng_title) {
        this.eng_title = eng_title;
    }

    public void setTitle(String chi_title, String eng_title) {
        setChi_title(chi_title);
        setEng_title(eng_title);
    }

    public void setTitle(String title) {
        String chi_title = title;
        String eng_title = "";
        for (int i = 0; i < title.length(); i++) {
            char c = title.charAt(i);
            if (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z')) {
                chi_title = title.substring(0, i);
                eng_title = title.substring(i);
                break;
            }
        }
        setTitle(chi_title, eng_title);
    }

    public String toJsonString() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("chi_title", chi_title);
        object.put("eng_title", eng_title);
        return object.toString();
    }

    public static Course fromJson(String jsonStr) throws JSONException {
        Course course = new Course();
        JSONObject courseJson = new JSONObject(jsonStr);
        course.id = courseJson.getLong("id");
        course.chi_title = courseJson.getString("chi_title");
        course.eng_title = courseJson.getString("eng_title");
        return course;
    }
}
