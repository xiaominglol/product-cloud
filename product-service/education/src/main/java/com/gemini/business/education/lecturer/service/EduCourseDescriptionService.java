package com.gemini.business.education.lecturercation.lecturer.service;

import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.education.lecturercation.lecturer.mapper.EduCourseDescriptionMapper;
import com.gemini.business.education.lecturercation.lecturer.po.EduCourseDescriptionPo;

/**
 * 课程简介
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:45:30 CST 2020
 */
public interface EduCourseDescriptionService extends BaseDetailService<EduCourseDescriptionPo, EduCourseDescriptionPo, EduCourseDescriptionMapper, EduCourseDescriptionMapper> {
}
