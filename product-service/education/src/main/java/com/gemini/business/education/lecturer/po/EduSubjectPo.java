package com.gemini.business.education.lecturercation.lecturer.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 课程科目
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:45:30 CST 2020
 */
@Data
@TableName("edu_subject")
public class EduSubjectPo {

    private Long id;
    /**
     * 类别名称
     */
    private String title;

    /**
     * 父ID
     */
    private String parentId;

    /**
     * 排序字段
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
