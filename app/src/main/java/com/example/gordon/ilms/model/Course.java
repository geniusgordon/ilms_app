package com.example.gordon.ilms.model;

/**
 * Created by gordon on 9/27/15.
 */
public class Course {

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
}
