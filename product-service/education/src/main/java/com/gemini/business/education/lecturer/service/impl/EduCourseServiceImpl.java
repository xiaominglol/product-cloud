package com.gemini.business.education.lecturercation.lecturer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.education.lecturercation.lecturer.mapper.EduCourseMapper;
import com.gemini.business.education.lecturercation.lecturer.po.EduCoursePo;
import com.gemini.business.education.lecturercation.lecturer.service.EduCourseService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 课程
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:45:30 CST 2020
 */
@Service
public class EduCourseServiceImpl extends BaseDetailServiceImpl<EduCoursePo, EduCoursePo, EduCourseMapper, EduCourseMapper> implements EduCourseService {

    @Override
    public QueryWrapper<EduCoursePo> wrapper(EduCoursePo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getTeacherId()), "teacher_id", po.getTeacherId())
                .eq(!StringUtils.isEmpty(po.getSubjectId()), "subject_id", po.getSubjectId())
                .eq(!StringUtils.isEmpty(po.getSubjectParentId()), "subject_parent_id", po.getSubjectParentId())
                .eq(!StringUtils.isEmpty(po.getTitle()), "title", po.getTitle())
                .eq(!StringUtils.isEmpty(po.getPrice()), "price", po.getPrice())
                .eq(!StringUtils.isEmpty(po.getLessonNum()), "lesson_num", po.getLessonNum())
                .eq(!StringUtils.isEmpty(po.getCover()), "cover", po.getCover())
                .eq(!StringUtils.isEmpty(po.getBuyCount()), "buy_count", po.getBuyCount())
                .eq(!StringUtils.isEmpty(po.getViewCount()), "view_count", po.getViewCount())
                .eq(!StringUtils.isEmpty(po.getVersion()), "version", po.getVersion())
                .eq(!StringUtils.isEmpty(po.getStatus()), "status", po.getStatus())
                .eq(!StringUtils.isEmpty(po.getIsDeleted()), "is_deleted", po.getIsDeleted())
                .eq(!StringUtils.isEmpty(po.getGmtCreate()), "gmt_create", po.getGmtCreate())
                .eq(!StringUtils.isEmpty(po.getGmtModified()), "gmt_modified", po.getGmtModified());
    }
}
