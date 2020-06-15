package com.study.app.data;

import com.study.app.R;
import com.study.app.bean.Course;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    //课程类型
    String[] CourseType = new String[]{
            "数学",
            "语文",
            "历史",
            "物理",
            "化学",
            "英语",
    };

    //课程内容
    String[] CourseContext = new String[]{

    };


    //链接
    public String[] DEFAULT_URL = new String[]{

    };

    //课程图片
    public int[] DEFAULT_PIC = new int[]{
            R.drawable.treelesion_1,
            R.drawable.treelesion_2,
            R.drawable.treelesion_3,
            R.drawable.treelesion_4,
            R.drawable.treelesion_5,
            R.drawable.treelesion_1,
            R.drawable.treelesion_1,
            R.drawable.treelesion_1,
            R.drawable.treelesion_1,
            R.drawable.treelesion_1,
    };


    public List<Course> mCourseInfoList = new ArrayList<>();

    public DataBase(){
        for (int i=0;i<CourseType.length;i++){
            Course course = new Course();
            treeLesion.setTREELESION_ID(getRandomTree_ID());
            treeLesion.setTREELESION_TYPE(TreeLesionsType[i]);
            treeLesion.setTREELESION_CONTEX(TreeLesionsContext[i]);
            treeLesion.setTREELESION_PIC_ID(DEFAULT_PIC[i]);
            treeLesion.setTREELESION_URL(DEFAULT_URL[i]);
            mTreeLesionInfoList.add(treeLesion);
        }

        for (int i=0;i<PestType.length;i++){
            Pest pest = new Pest();
            pest.setPEST_ID(getRandomPest_ID());
            pest.setPEST_TYPE(PestType[i]);
            pest.setPEST_CONTEX(PestContext[i]);
            pest.setPEST_PIC_ID(PEST_DEFAULT_PIC[i]);
            pest.setPEST_URL(PEST_DEFAULT_URL[i]);
            mPestInfoList.add(pest);
        }
    }

    //生成树木id
    public static String getRandomTree_ID(){
        String strRand="T" ;
        for(int i=0;i<10;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }

    //生成害虫id
    public static String getRandomPest_ID(){
        String strRand="P" ;
        for(int i=0;i<10;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }
}
