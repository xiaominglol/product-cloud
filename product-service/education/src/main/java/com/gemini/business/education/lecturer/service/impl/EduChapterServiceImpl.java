package com.gemini.business.education.lecturercation.lecturer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.education.lecturercation.lecturer.mapper.EduChapterMapper;
import com.gemini.business.education.lecturercation.lecturer.po.EduChapterPo;
import com.gemini.business.education.lecturercation.lecturer.service.EduChapterService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 课程
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:45:30 CST 2020
 */
@Service
public class EduChapterServiceImpl extends BaseDetailServiceImpl<EduChapterPo, EduChapterPo, EduChapterMapper, EduChapterMapper> implements EduChapterService {

    @Override
    public QueryWrapper<EduChapterPo> wrapper(EduChapterPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getCourseId()), "course_id", po.getCourseId())
                .eq(!StringUtils.isEmpty(po.getTitle()), "title", po.getTitle())
                .eq(!StringUtils.isEmpty(po.getSort()), "sort", po.getSort())
                .eq(!StringUtils.isEmpty(po.getGmtCreate()), "gmt_create", po.getGmtCreate())
                .eq(!StringUtils.isEmpty(po.getGmtModified()), "gmt_modified", po.getGmtModified());
    }
}
