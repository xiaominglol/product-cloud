package com.gemini.business.education.lecturercation.lecturer.service;

import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.education.lecturercation.lecturer.mapper.EduVideoMapper;
import com.gemini.business.education.lecturercation.lecturer.po.EduVideoPo;

/**
 * 课程视频
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:45:30 CST 2020
 */
public interface EduVideoService extends BaseDetailService<EduVideoPo, EduVideoPo, EduVideoMapper, EduVideoMapper> {
}
