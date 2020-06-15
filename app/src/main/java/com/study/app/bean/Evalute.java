package com.study.app.bean;

import java.io.Serializable;

public class Evalute implements Serializable {
    public String EVA_ID;
    public String USER_ID;
    public String COURSE_ID;
    public String EVA_CONTENT;
    public String EVA_TIME;

    public String getEVA_ID() {
        return EVA_ID;
    }

    public void setEVA_ID(String EVA_ID) {
        this.EVA_ID = EVA_ID;
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

    public String getEVA_CONTENT() {
        return EVA_CONTENT;
    }

    public void setEVA_CONTENT(String EVA_CONTENT) {
        this.EVA_CONTENT = EVA_CONTENT;
    }

    public String getEVA_TIME() {
        return EVA_TIME;
    }

    public void setEVA_TIME(String EVA_TIME) {
        this.EVA_TIME = EVA_TIME;
    }
}
