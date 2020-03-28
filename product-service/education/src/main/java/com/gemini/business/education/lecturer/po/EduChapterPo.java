package com.gemini.business.education.lecturercation.lecturer.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 课程
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:45:30 CST 2020
 */
@Data
@TableName("edu_chapter")
public class EduChapterPo {

    private Long id;
    /**
     * 课程ID
     */
    private String courseId;

    /**
     * 章节名称
     */
    private String title;

    /**
     * 显示排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;
}
