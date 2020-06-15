package com.study.app.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course  implements Serializable {
    String COURSE_ID;
    String COURSE_TYPE;
    String COURSE_CONTEX;
    String COURSE_URL;
    String COURSE_PIC_ID;

    List<Chapter> mChapters = new ArrayList<>();

    public String getCOURSE_ID() {
        return COURSE_ID;
    }

    public void setCOURSE_ID(String COURSE_ID) {
        this.COURSE_ID = COURSE_ID;
    }

    public String getCOURSE_TYPE() {
        return COURSE_TYPE;
    }

    public void setCOURSE_TYPE(String COURSE_TYPE) {
        this.COURSE_TYPE = COURSE_TYPE;
    }

    public String getCOURSE_CONTEX() {
        return COURSE_CONTEX;
    }

    public void setCOURSE_CONTEX(String COURSE_CONTEX) {
        this.COURSE_CONTEX = COURSE_CONTEX;
    }

    public String getCOURSE_URL() {
        return COURSE_URL;
    }

    public void setCOURSE_URL(String COURSE_URL) {
        this.COURSE_URL = COURSE_URL;
    }

    public String getCOURSE_PIC_ID() {
        return COURSE_PIC_ID;
    }

    public void setCOURSE_PIC_ID(String COURSE_PIC_ID) {
        this.COURSE_PIC_ID = COURSE_PIC_ID;
    }

    public List<Chapter> getmChapters() {
        return mChapters;
    }

    public void setmChapters(List<Chapter> mChapters) {
        this.mChapters = mChapters;
    }

    public String getProgress(){
        int index = mChapters.size();
        int chapter_finish = 0;
        if (index == 0){
            return "无章节内容";
        }else {
            for (int i =0;i<mChapters.size();i++){
                Chapter chapter = mChapters.get(i);
                if (chapter.getCHAPTER_FINISH().equals("已完成")){
                    chapter_finish++;
                }
            }
            return "已完成"+chapter_finish+"/"+mChapters.size();
        }

    }
}
