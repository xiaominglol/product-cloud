package com.gemini.business.education.lecturercation.lecturer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.education.lecturercation.lecturer.mapper.EduSubjectMapper;
import com.gemini.business.education.lecturercation.lecturer.po.EduSubjectPo;
import com.gemini.business.education.lecturercation.lecturer.service.EduSubjectService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 课程科目
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:45:30 CST 2020
 */
@Service
public class EduSubjectServiceImpl extends BaseDetailServiceImpl<EduSubjectPo, EduSubjectPo, EduSubjectMapper, EduSubjectMapper> implements EduSubjectService {

    @Override
    public QueryWrapper<EduSubjectPo> wrapper(EduSubjectPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getTitle()), "title", po.getTitle())
                .eq(!StringUtils.isEmpty(po.getParentId()), "parent_id", po.getParentId())
                .eq(!StringUtils.isEmpty(po.getSort()), "sort", po.getSort())
                .eq(!StringUtils.isEmpty(po.getGmtCreate()), "gmt_create", po.getGmtCreate())
                .eq(!StringUtils.isEmpty(po.getGmtModified()), "gmt_modified", po.getGmtModified());
    }
}
