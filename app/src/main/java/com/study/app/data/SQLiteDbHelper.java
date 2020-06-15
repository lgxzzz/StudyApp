package com.study.app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.study.app.util.SharedPreferenceUtil;


public class SQLiteDbHelper extends SQLiteOpenHelper {

    //数据库名称
    public static final String DB_NAME = "StudyApp.db";
    //数据库版本号
    public static int DB_VERSION = 10;
    //用户表
    public static final String TAB_USER = "UserInfo";
    //课程表
    public static final String TAB_COURSE = "Course";
    //课程章节表
    public static final String TAB_CHAPTER = "Chapter";
    //评价表
    public static final String TAB_EVALUATE = "Evalute";
    //学习表
    public static final String TAB_STUDY = "Study";

    Context context;
    public SQLiteDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableUser(db);
        createTableCourse(db);
        createTableChapter(db);
        createTableEvalute(db);
        createTableStudy(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        SharedPreferenceUtil.setFirstTimeUse(true,context);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_COURSE);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_CHAPTER);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_EVALUATE);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_STUDY);

        onCreate(db);
    }

    //创建人员表
    public void createTableUser(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_USER +
                "(USER_ID varchar(20) primary key, " +
                "USER_NAME varchar(20), " +
                "USER_PASSWORD varchar(20), " +
                "USER_TEL varchar(20), " +
                "USER_MAIL varchar(20), " +
                "USER_ROLE varchar(20))");
    }

    //创建课程表
    public void createTableCourse(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_COURSE +
                "(COURSE_ID varchar(20) primary key, " +
                "COURSE_TYPE varchar(20), " +  //种类
                "COURSE_CONTEX varchar(20), " + //内容
                "COURSE_URL varchar(20), " +  // 文章链接
                "COURSE_PIC_ID varchar(20))");//图片id
    }

    //创建进度表
    public void createTableChapter(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_CHAPTER +
                "(CHAPTER_ID varchar(20) primary key, " +//进度id
                "COURSE_ID varchar(20), " +  //对应的课程id
                "CHAPTER_NAME varchar(20), " + //章节名
                "CHAPTER_URL varchar(20), " + //章节链接
                "CHAPTER_FINISH varchar(20), " + //是否学习完成. 是 or 否
                "CHAPTER_PIC_ID varchar(20))"); //章节图片id
    }

    //创建评价表
    public void createTableEvalute(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_EVALUATE +
                "(EVA_ID varchar(20) primary key, " + //评论ID
                "USER_ID varchar(20), " + //评论用户id
                "COURSE_ID varchar(20), " + //评论课程id
                "EVA_CONTENT varchar(20), " + //评论内容
                "EVA_TIME varchar(20))"); //评论时间
    }

    //创建学习表
    public void createTableStudy(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_STUDY +
                "(STUDY_ID varchar(20) primary key, " + //评论ID
                "USER_ID varchar(20), " + //用户id
                "COURSE_ID varchar(20), " + //课程id
                "STUDY_TIME varchar(20))"); //学习时间
    }
}
