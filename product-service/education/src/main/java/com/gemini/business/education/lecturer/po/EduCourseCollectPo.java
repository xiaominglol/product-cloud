package com.gemini.business.education.lecturercation.lecturer.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 课程收藏
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:45:30 CST 2020
 */
@Data
@TableName("edu_course_collect")
public class EduCourseCollectPo {

    private Long id;
    /**
     * 课程讲师ID
     */
    private String courseId;

    /**
     * 课程专业ID
     */
    private String memberId;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    private Byte isDeleted;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;
}
