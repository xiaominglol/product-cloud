package com.gemini.business.education.lecturercation.lecturer.service;

import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.education.lecturercation.lecturer.mapper.EduSubjectMapper;
import com.gemini.business.education.lecturercation.lecturer.po.EduSubjectPo;

/**
 * 课程科目
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:45:30 CST 2020
 */
public interface EduSubjectService extends BaseDetailService<EduSubjectPo, EduSubjectPo, EduSubjectMapper, EduSubjectMapper> {
}
