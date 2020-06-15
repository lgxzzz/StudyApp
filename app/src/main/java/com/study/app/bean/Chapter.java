package com.study.app.bean;

import java.io.Serializable;

public class Chapter implements Serializable {
    public String CHAPTER_ID;
    public String COURSE_ID;
    public String CHAPTER_NAME;
    public String CHAPTER_URL;
    public String CHAPTER_PIC_ID;
    public String CHAPTER_FINISH;

    public String getCHAPTER_FINISH() {
        return CHAPTER_FINISH;
    }

    public void setCHAPTER_FINISH(String CHAPTER_FINISH) {
        this.CHAPTER_FINISH = CHAPTER_FINISH;
    }

    public String getCHAPTER_ID() {
        return CHAPTER_ID;
    }

    public void setCHAPTER_ID(String CHAPTER_ID) {
        this.CHAPTER_ID = CHAPTER_ID;
    }

    public String getCOURSE_ID() {
        return COURSE_ID;
    }

    public void setCOURSE_ID(String COURSE_ID) {
        this.COURSE_ID = COURSE_ID;
    }

    public String getCHAPTER_NAME() {
        return CHAPTER_NAME;
    }

    public void setCHAPTER_NAME(String CHAPTER_NAME) {
        this.CHAPTER_NAME = CHAPTER_NAME;
    }

    public String getCHAPTER_URL() {
        return CHAPTER_URL;
    }

    public void setCHAPTER_URL(String CHAPTER_URL) {
        this.CHAPTER_URL = CHAPTER_URL;
    }

    public String getCHAPTER_PIC_ID() {
        return CHAPTER_PIC_ID;
    }

    public void setCHAPTER_PIC_ID(String CHAPTER_PIC_ID) {
        this.CHAPTER_PIC_ID = CHAPTER_PIC_ID;
    }
}
