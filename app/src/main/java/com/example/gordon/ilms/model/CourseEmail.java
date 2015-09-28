package com.example.gordon.ilms.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 9/29/15.
 */
public class CourseEmail {

    private List<Email> emails;

    public CourseEmail() {
        emails = new ArrayList<Email>();
    }

    public void addEmail(Email email) {
        emails.add(email);
    }

    public List<Email> getEmails() {
        return emails;
    }

    public static class Email {
        private String name;
        private String email;
        private boolean isProfessor;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isProfessor() {
            return isProfessor;
        }

        public void setIsProfessor(boolean isProfessor) {
            this.isProfessor = isProfessor;
        }
    }
}
