package com.study.app.data;

import com.study.app.R;
import com.study.app.bean.Chapter;
import com.study.app.bean.Course;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    //课程类型
    String[] CourseType = new String[]{
            "数学",
            "历史",
            "化学",
            "语文",
            "物理",
            "英语",
    };

    //课程内容
    String[] CourseContext = new String[]{
            "数学（mathematics或maths，其英文来自希腊语，“máthēma”；经常被缩写为“math”），是研究数量、结构、变化、空间以及信息等概念的一门学科，从某种角度看属于形式科学的一种。数学家和哲学家对数学的确切范围和定义有一系列的看法。",
            "历史，简称“史”，指对人类社会过去的事件和活动，以及对这些事件行为有系统的记录、研究和诠释。历史是客观存在的，无论文学家们如何书写历史，历史都以自己的方式存在，不可改变。",
            "化学是自然科学的一种，主要在分子、原子层面，研究物质的组成、性质、结构与变化规律，创造新物质。",
            "语文课程，是一门博大精深的学科，需要有学习兴趣，好的学习习惯等都是很重要的。学好语文课程，要注意培养学习兴趣，养成好的学习习惯，积累学习方法，增强学习能力等,语文课程的基本特点，是人文性与工具性的统一，有一定的审美性与实用性。",
            "物理学是研究物质运动最一般规律和物质基本结构的学科。作为自然科学的带头学科，物理学研究大至宇宙，小至基本粒子等一切物质最基本的运动形式和规律，因此成为其他各自然科学学科的研究基础。",
            "英语是按照分布面积而言最流行的语言且母语者数量是世界第三，仅次于汉语，西班牙语。它是学习最广泛的第二语言，是近60个主权国家的官方语言或官方语言之一。与英语为母语的人相比，将其作为第二语言学习的人更多。它是英国，美国，加拿大，澳大利亚，新西兰等国家的母语，在加勒比海，非洲和南亚被广泛使用。它是联合国，欧洲联盟以及许多其他世界和区域国际组织的官方语言之一。 [1] "
    };


    //链接
    public String[] DEFAULT_URL = new String[]{
            "https://zky.koocdn.com/guonei-college/picture/aa0c9b02db5645dc882e904cc9577884.pdf",
            "https://kaoyan.koolearn.com/20200609/1065642.html",
            "https://zhongkao.koolearn.com/20200611/955646.html",
            "https://zhongkao.koolearn.com/20190623/951765.html",
            "https://kaoyan.koolearn.com/20200604/1069340.html",
            "https://cet4.koolearn.com/20200615/840358.html"
    };

    //课程图片
    public int[] DEFAULT_PIC = new int[]{
            R.drawable.shuxue,
            R.drawable.lishi,
            R.drawable.huaxue,
            R.drawable.yuwen,
            R.drawable.wuli,
            R.drawable.yingyu,
    };


    //章节名称
    String[] ChapterType = new String[]{
            "第一章",
            "第二章",
            "第三章",
            "第四章",
            "第五章",
            "第六章",
    };

    //链接
    public String[] DEFAULT_CHAPTER_URL = new String[]{
            "https://zky.koocdn.com/guonei-college/picture/aa0c9b02db5645dc882e904cc9577884.pdf",
            "https://kaoyan.koolearn.com/20200609/1065642.html",
            "https://zhongkao.koolearn.com/20200611/955646.html",
            "https://zhongkao.koolearn.com/20190623/951765.html",
            "https://kaoyan.koolearn.com/20200604/1069340.html",
            "https://cet4.koolearn.com/20200615/840358.html"
    };

    public List<Course> mCourseInfoList = new ArrayList<>();
    public List<Chapter> mChapterInfoList = new ArrayList<>();

    public DataBase(){
        for (int i=0;i<CourseType.length;i++){
            Course course = new Course();
            String courseid = getRandomCourse_ID();
            String course_name = CourseType[i];
            course.setCOURSE_ID(courseid);
            course.setCOURSE_TYPE(course_name);
            course.setCOURSE_CONTEX(CourseContext[i]);
            course.setCOURSE_PIC_ID(DEFAULT_PIC[i]+"");
            course.setCOURSE_URL(DEFAULT_URL[i]);
            mCourseInfoList.add(course);

            for (int j=0;j<ChapterType.length;j++){
                Chapter chapter = new Chapter();
                chapter.setCHAPTER_ID(getRandomChapter_ID());
                chapter.setCOURSE_ID(courseid);
                chapter.setCHAPTER_NAME(ChapterType[j]);
                chapter.setCHAPTER_URL(DEFAULT_CHAPTER_URL[i]);
                chapter.setCHAPTER_PIC_ID("");
                chapter.setCHAPTER_FINISH("未完成");
                mChapterInfoList.add(chapter);
            }
        }


    }

    //生成课程id
    public static String getRandomCourse_ID(){
        String strRand="C" ;
        for(int i=0;i<10;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }

    //生成章节id
    public static String getRandomChapter_ID(){
        String strRand="Ch" ;
        for(int i=0;i<10;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }
}
