package com.study.app.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.study.app.bean.Chapter;
import com.study.app.bean.Course;
import com.study.app.bean.Evalute;
import com.study.app.bean.Study;
import com.study.app.bean.User;
import com.study.app.util.SharedPreferenceUtil;


import java.util.ArrayList;
import java.util.List;

public class DBManger {
    private Context mContext;
    private SQLiteDbHelper mDBHelper;
    public User mUser;
    public DataBase mDataBase;
    public static  DBManger instance;
    public static DBManger getInstance(Context mContext){
        if (instance == null){
            instance = new DBManger(mContext);
        }
        return instance;
    };

    public DBManger(final Context mContext){
        this.mContext = mContext;
        mDBHelper = new SQLiteDbHelper(mContext);
        mDataBase = new DataBase();
        if (SharedPreferenceUtil.getFirstTimeUse(mContext)){
            initDefaultData();
            SharedPreferenceUtil.setFirstTimeUse(false,mContext);
        }
    }


    //用户登陆
    public void login(String name, String password, IListener listener){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from UserInfo where USER_NAME =? and USER_PASSWORD=?",new String[]{name,password});
            if (cursor.moveToFirst()){
                String USER_ID = cursor.getString(cursor.getColumnIndex("USER_ID"));
                String USER_NAME = cursor.getString(cursor.getColumnIndex("USER_NAME"));
                String USER_MAIL = cursor.getString(cursor.getColumnIndex("USER_MAIL"));
                String USER_TEL = cursor.getString(cursor.getColumnIndex("USER_TEL"));
                String USER_ROLE = cursor.getString(cursor.getColumnIndex("USER_ROLE"));

                mUser = new User();
                mUser.setUserId(USER_ID);
                mUser.setUserName(USER_NAME);
                mUser.setTelephone(USER_TEL);
                listener.onSuccess();
            }else{
                listener.onError("未查询到该用户");
            }
            db.close();
            return;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        listener.onError("未查询到该用户");
    }

    //修改用户信息
    public void updateUser(User user,IListener listener){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from UserInfo where USER_NAME=?",new String[]{user.getUserName()});
            if (cursor.moveToFirst()){
                ContentValues values = new ContentValues();
                values.put("USER_NAME",user.getUserName());
                values.put("USER_TEL",user.getTelephone());
                values.put("USER_PASSWORD",user.getPassword());

                int code = db.update(SQLiteDbHelper.TAB_USER,values,"USER_NAME =?",new String[]{user.getUserName()+""});
                listener.onSuccess();
            }else {
                insertUser(user,listener);
            }
            db.close();
        }catch (Exception e){

        }
    }

    public boolean isStudy(String course_id){
        boolean flag = false;
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from Study where COURSE_ID =?",new String[]{course_id});
            if (cursor.moveToFirst()){
                flag = true;
            }else{
                flag = false;
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    //注册用户
    public void registerUser(User user,IListener listener){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from UserInfo where USER_NAME=?",new String[]{user.getUserName()});
            if (cursor.moveToFirst()){
                listener.onError("用户名已经被注册！");
            }else{
                String userid = getRandomUSER_ID();
                ContentValues values = new ContentValues();
                values.put("USER_ID",userid);
                values.put("USER_NAME",user.getUserName());
                values.put("USER_PASSWORD",user.getPassword());
                mUser = user;
                mUser.setUserId(userid);
                mUser.setUserName(user.getUserName());
                mUser.setTelephone(user.getTelephone());
                long code = db.insert(SQLiteDbHelper.TAB_USER,null,values);
                listener.onSuccess();
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    };

    //注册用户
    public void insertUser(User user,IListener listener){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from UserInfo where USER_NAME=?",new String[]{user.getUserName()});
            if (cursor.moveToFirst()){
                listener.onError("用户名已经被注册！");
            }else{
                String userid = getRandomUSER_ID();
                ContentValues values = new ContentValues();
                values.put("USER_ID",userid);
                values.put("USER_NAME",user.getUserName());
                values.put("USER_PASSWORD",user.getPassword());
                values.put("USER_TEL",user.getTelephone());
                long code = db.insert(SQLiteDbHelper.TAB_USER,null,values);
                listener.onSuccess();
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    };


    //生成随机userid
    public String getRandomUSER_ID(){
        String strRand="LF" ;
        for(int i=0;i<10;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }

    //根据条件查询课程
    public User getUserByID(String userid){
        User user = null;
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from UserInfo where USER_ID=?",new String[]{userid});
            while (cursor.moveToNext()){
                String USER_ID = cursor.getString(cursor.getColumnIndex("USER_ID"));
                String USER_NAME = cursor.getString(cursor.getColumnIndex("USER_NAME"));
                String USER_PASSWORD = cursor.getString(cursor.getColumnIndex("USER_PASSWORD"));
                String USER_TEL = cursor.getString(cursor.getColumnIndex("USER_TEL"));
                String USER_MAIL = cursor.getString(cursor.getColumnIndex("USER_MAIL"));
                String USER_ROLE = cursor.getString(cursor.getColumnIndex("USER_ROLE"));
                user = new User();
                user.setUserId(USER_ID);
                user.setUserName(USER_NAME);
                user.setPassword(USER_PASSWORD);
                user.setTelephone(USER_TEL);
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }




    //获取所有用户
    public List<User> getAllUsers(){
        List<User> mUsers = new ArrayList<>();
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.query(SQLiteDbHelper.TAB_USER,null,null,null,null,null,null);
            while (cursor.moveToNext()){
                String USER_ID = cursor.getString(cursor.getColumnIndex("USER_ID"));
                String USER_NAME = cursor.getString(cursor.getColumnIndex("USER_NAME"));
                String USER_PASSWORD = cursor.getString(cursor.getColumnIndex("USER_PASSWORD"));
                String USER_TEL = cursor.getString(cursor.getColumnIndex("USER_TEL"));
                String USER_MAIL = cursor.getString(cursor.getColumnIndex("USER_MAIL"));
                String USER_ROLE = cursor.getString(cursor.getColumnIndex("USER_ROLE"));

                User user = new User();
                user.setUserId(USER_ID);
                user.setUserName(USER_NAME);
                user.setPassword(USER_PASSWORD);
                user.setTelephone(USER_TEL);
                mUsers.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mUsers;
    }

    //添加课程
    public void insertCourse(Course course){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("COURSE_ID",course.getCOURSE_ID());
            values.put("COURSE_TYPE",course.getCOURSE_TYPE());
            values.put("COURSE_CONTEX",course.getCOURSE_CONTEX());
            values.put("COURSE_URL",course.getCOURSE_URL());
            values.put("COURSE_PIC_ID",course.getCOURSE_PIC_ID());
            long code = db.insert(SQLiteDbHelper.TAB_COURSE,null,values);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //添加章节
    public void insertChapter(Chapter chapter){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("CHAPTER_ID",chapter.getCHAPTER_ID());
            values.put("COURSE_ID",chapter.getCOURSE_ID());
            values.put("CHAPTER_NAME",chapter.getCHAPTER_NAME());
            values.put("CHAPTER_URL",chapter.getCHAPTER_URL());
            values.put("CHAPTER_PIC_ID",chapter.getCHAPTER_PIC_ID());
            values.put("CHAPTER_FINISH",chapter.getCHAPTER_PIC_ID());
            long code = db.insert(SQLiteDbHelper.TAB_CHAPTER,null,values);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有课程信息
    public List<Course> getAllCourse(){
        List<Course> mAllCourseList = new ArrayList<>();
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.query(SQLiteDbHelper.TAB_COURSE,null,null,null,null,null,null);
            while (cursor.moveToNext()){
                String COURSE_ID = cursor.getString(cursor.getColumnIndex("COURSE_ID"));
                String COURSE_TYPE = cursor.getString(cursor.getColumnIndex("COURSE_TYPE"));
                String COURSE_CONTEX = cursor.getString(cursor.getColumnIndex("COURSE_CONTEX"));
                String COURSE_URL = cursor.getString(cursor.getColumnIndex("COURSE_URL"));
                String COURSE_PIC_ID = cursor.getString(cursor.getColumnIndex("COURSE_PIC_ID"));

                Course course = new Course();
                course.setCOURSE_ID(COURSE_ID);
                course.setCOURSE_TYPE(COURSE_TYPE);
                course.setCOURSE_CONTEX(COURSE_CONTEX);
                course.setCOURSE_URL(COURSE_URL);
                course.setCOURSE_PIC_ID(COURSE_PIC_ID);

                List<Chapter> mChapters = getChaptersById(COURSE_ID);
                course.setmChapters(mChapters);

                mAllCourseList.add(course);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mAllCourseList;
    }

    //根据ID查询课程
    public Course getCoursesByID(String course_id){
        Course course = null;
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Course WHERE COURSE_ID LIKE '%" + course_id + "%'", null);
            while (cursor.moveToNext()){
                String COURSE_ID = cursor.getString(cursor.getColumnIndex("COURSE_ID"));
                String COURSE_TYPE = cursor.getString(cursor.getColumnIndex("COURSE_TYPE"));
                String COURSE_CONTEX = cursor.getString(cursor.getColumnIndex("COURSE_CONTEX"));
                String COURSE_URL = cursor.getString(cursor.getColumnIndex("COURSE_URL"));
                String COURSE_PIC_ID = cursor.getString(cursor.getColumnIndex("COURSE_PIC_ID"));

                course = new Course();
                course.setCOURSE_ID(COURSE_ID);
                course.setCOURSE_TYPE(COURSE_TYPE);
                course.setCOURSE_CONTEX(COURSE_CONTEX);
                course.setCOURSE_URL(COURSE_URL);
                course.setCOURSE_PIC_ID(COURSE_PIC_ID);

                List<Chapter> mChapters = getChaptersById(COURSE_ID);
                course.setmChapters(mChapters);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return course;
    }

    //根据条件查询课程
    public List<Course> getCoursesByKey(String key){
        List<Course> mAllCourseList = new ArrayList<>();
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Course WHERE COURSE_TYPE LIKE '%" + key + "%'", null);
            while (cursor.moveToNext()){
                String COURSE_ID = cursor.getString(cursor.getColumnIndex("COURSE_ID"));
                String COURSE_TYPE = cursor.getString(cursor.getColumnIndex("COURSE_TYPE"));
                String COURSE_CONTEX = cursor.getString(cursor.getColumnIndex("COURSE_CONTEX"));
                String COURSE_URL = cursor.getString(cursor.getColumnIndex("COURSE_URL"));
                String COURSE_PIC_ID = cursor.getString(cursor.getColumnIndex("COURSE_PIC_ID"));

                Course course = new Course();
                course.setCOURSE_ID(COURSE_ID);
                course.setCOURSE_TYPE(COURSE_TYPE);
                course.setCOURSE_CONTEX(COURSE_CONTEX);
                course.setCOURSE_URL(COURSE_URL);
                course.setCOURSE_PIC_ID(COURSE_PIC_ID);

                List<Chapter> mChapters = getChaptersById(COURSE_ID);
                course.setmChapters(mChapters);

                mAllCourseList.add(course);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mAllCourseList;
    }

    //修改用户信息
    public void updateChapter(Chapter chapter){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("CHAPTER_ID",chapter.getCHAPTER_ID());
            values.put("COURSE_ID",chapter.getCOURSE_ID());
            values.put("CHAPTER_NAME",chapter.getCHAPTER_NAME());
            values.put("CHAPTER_URL",chapter.getCHAPTER_URL());
            values.put("CHAPTER_PIC_ID",chapter.getCHAPTER_PIC_ID());
            values.put("CHAPTER_FINISH",chapter.getCHAPTER_FINISH());
            int code = db.update(SQLiteDbHelper.TAB_CHAPTER,values,"CHAPTER_ID =?",new String[]{chapter.getCHAPTER_ID()+""});
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //根据ID查询对应的章节
    public List<Chapter> getChaptersById(String course_id){
        List<Chapter> mChapters = new ArrayList<>();
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Chapter WHERE COURSE_ID LIKE '%" + course_id + "%'", null);
            while (cursor.moveToNext()){
                String CHAPTER_ID = cursor.getString(cursor.getColumnIndex("CHAPTER_ID"));
                String COURSE_ID = cursor.getString(cursor.getColumnIndex("COURSE_ID"));
                String CHAPTER_NAME = cursor.getString(cursor.getColumnIndex("CHAPTER_NAME"));
                String CHAPTER_URL = cursor.getString(cursor.getColumnIndex("CHAPTER_URL"));
                String CHAPTER_PIC_ID = cursor.getString(cursor.getColumnIndex("CHAPTER_PIC_ID"));
                String CHAPTER_FINISH = cursor.getString(cursor.getColumnIndex("CHAPTER_FINISH"));

                Chapter chapter = new Chapter();
                chapter.setCHAPTER_ID(CHAPTER_ID);
                chapter.setCOURSE_ID(COURSE_ID);
                chapter.setCHAPTER_NAME(CHAPTER_NAME);
                chapter.setCHAPTER_URL(CHAPTER_URL);
                chapter.setCHAPTER_PIC_ID(CHAPTER_PIC_ID);
                chapter.setCHAPTER_FINISH(CHAPTER_FINISH);
                mChapters.add(chapter);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mChapters;
    }

    //添加评论
    public void insertEvalute(Evalute evalute){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("EVA_ID",evalute.getEVA_ID());
            values.put("USER_ID",evalute.getUSER_ID());
            values.put("COURSE_ID",evalute.getCOURSE_ID());
            values.put("EVA_CONTENT",evalute.getEVA_CONTENT());
            values.put("EVA_TIME",evalute.getEVA_TIME());
            long code = db.insert(SQLiteDbHelper.TAB_EVALUATE,null,values);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //根据课程id获取评价
    public List<Evalute> getEvalutesByCourseId(String course_id){
        List<Evalute> mAllEvaList = new ArrayList<>();
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Evalute WHERE COURSE_ID LIKE '%" + course_id + "%'", null);
            while (cursor.moveToNext()){
                String EVA_ID = cursor.getString(cursor.getColumnIndex("EVA_ID"));
                String USER_ID = cursor.getString(cursor.getColumnIndex("USER_ID"));
                String COURSE_ID = cursor.getString(cursor.getColumnIndex("COURSE_ID"));
                String EVA_CONTENT = cursor.getString(cursor.getColumnIndex("EVA_CONTENT"));
                String EVA_TIME = cursor.getString(cursor.getColumnIndex("EVA_TIME"));

                Evalute evalute = new Evalute();
                evalute.setEVA_ID(EVA_ID);
                evalute.setUSER_ID(USER_ID);
                evalute.setCOURSE_ID(COURSE_ID);
                evalute.setEVA_CONTENT(EVA_CONTENT);
                evalute.setEVA_TIME(EVA_TIME);
                mAllEvaList.add(evalute);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mAllEvaList;
    }

    //添加学习进度
    public void insertStudy(Study study){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("STUDY_ID",study.getSTUDY_ID());
            values.put("USER_ID",study.getUSER_ID());
            values.put("COURSE_ID",study.getCOURSE_ID());
            values.put("STUDY_TIME",study.getSTUDY_TIME());
            long code = db.insert(SQLiteDbHelper.TAB_STUDY,null,values);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有的学习内容
    public List<Study> getStudysByUserId(String user_id){
        List<Study> mStudys = new ArrayList<>();
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Study WHERE USER_ID LIKE '%" + user_id + "%'", null);
            while (cursor.moveToNext()){
                String STUDY_ID = cursor.getString(cursor.getColumnIndex("STUDY_ID"));
                String USER_ID = cursor.getString(cursor.getColumnIndex("USER_ID"));
                String COURSE_ID = cursor.getString(cursor.getColumnIndex("COURSE_ID"));
                String STUDY_TIME = cursor.getString(cursor.getColumnIndex("STUDY_TIME"));

                Study study = new Study();
                study.setSTUDY_ID(STUDY_ID);
                study.setUSER_ID(USER_ID);
                study.setCOURSE_ID(COURSE_ID);
                study.setSTUDY_TIME(STUDY_TIME);

                Course course = getCoursesByID(COURSE_ID);
                study.setCourse(course);
                mStudys.add(study);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mStudys;
    }

    public void initDefaultData(){
        List<Course> mCourseList = mDataBase.mCourseInfoList;
        for (int i =0;i<mCourseList.size();i++){
            Course course = mCourseList.get(i);
            insertCourse(course);
        }

        List<Chapter> mChapterList = mDataBase.mChapterInfoList;
        for (int i =0;i<mChapterList.size();i++){
            Chapter chapter = mChapterList.get(i);
            insertChapter(chapter);
        }
    }

    public interface IListener{
        public void onSuccess();
        public void onError(String error);
    };


}
