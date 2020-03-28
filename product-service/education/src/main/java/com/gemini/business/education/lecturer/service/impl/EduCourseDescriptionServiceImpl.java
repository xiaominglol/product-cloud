package com.gemini.business.education.lecturercation.lecturer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.education.lecturercation.lecturer.mapper.EduCourseDescriptionMapper;
import com.gemini.business.education.lecturercation.lecturer.po.EduCourseDescriptionPo;
import com.gemini.business.education.lecturercation.lecturer.service.EduCourseDescriptionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 课程简介
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:45:30 CST 2020
 */
@Service
public class EduCourseDescriptionServiceImpl extends BaseDetailServiceImpl<EduCourseDescriptionPo, EduCourseDescriptionPo, EduCourseDescriptionMapper, EduCourseDescriptionMapper> implements EduCourseDescriptionService {

    @Override
    public QueryWrapper<EduCourseDescriptionPo> wrapper(EduCourseDescriptionPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getDescription()), "description", po.getDescription())
                .eq(!StringUtils.isEmpty(po.getGmtCreate()), "gmt_create", po.getGmtCreate())
                .eq(!StringUtils.isEmpty(po.getGmtModified()), "gmt_modified", po.getGmtModified());
    }
}
