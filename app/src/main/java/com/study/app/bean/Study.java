package com.study.app.bean;

import java.io.Serializable;

public class Study implements Serializable {
    String STUDY_ID;
    String USER_ID;
    String COURSE_ID;
    String STUDY_TIME;

    Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSTUDY_ID() {
        return STUDY_ID;
    }

    public void setSTUDY_ID(String STUDY_ID) {
        this.STUDY_ID = STUDY_ID;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getCOURSE_ID() {
        return COURSE_ID;
    }

    public void setCOURSE_ID(String COURSE_ID) {
        this.COURSE_ID = COURSE_ID;
    }

    public String getSTUDY_TIME() {
        return STUDY_TIME;
    }

    public void setSTUDY_TIME(String STUDY_TIME) {
        this.STUDY_TIME = STUDY_TIME;
    }
}
