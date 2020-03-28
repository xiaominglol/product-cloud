package com.gemini.business.education.lecturercation.lecturer.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 课程简介
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:50:32 CST 2020
 */
@Data
@TableName("edu_course_description")
public class EduCourseDescriptionPo {

        private Long id;
        /**
         * 课程简介
         */
        private String description;

        /**
         * 创建时间
         */
        private Date gmtCreate;

        /**
         * 更新时间
         */
        private Date gmtModified;
}
