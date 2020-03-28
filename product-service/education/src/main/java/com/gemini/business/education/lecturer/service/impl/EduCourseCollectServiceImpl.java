package com.gemini.business.education.lecturercation.lecturer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.education.lecturercation.lecturer.mapper.EduCourseCollectMapper;
import com.gemini.business.education.lecturercation.lecturer.po.EduCourseCollectPo;
import com.gemini.business.education.lecturercation.lecturer.service.EduCourseCollectService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 课程收藏
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:45:30 CST 2020
 */
@Service
public class EduCourseCollectServiceImpl extends BaseDetailServiceImpl<EduCourseCollectPo, EduCourseCollectPo, EduCourseCollectMapper, EduCourseCollectMapper> implements EduCourseCollectService {

    @Override
    public QueryWrapper<EduCourseCollectPo> wrapper(EduCourseCollectPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getCourseId()), "course_id", po.getCourseId())
                .eq(!StringUtils.isEmpty(po.getMemberId()), "member_id", po.getMemberId())
                .eq(!StringUtils.isEmpty(po.getIsDeleted()), "is_deleted", po.getIsDeleted())
                .eq(!StringUtils.isEmpty(po.getGmtCreate()), "gmt_create", po.getGmtCreate())
                .eq(!StringUtils.isEmpty(po.getGmtModified()), "gmt_modified", po.getGmtModified());
    }
}
