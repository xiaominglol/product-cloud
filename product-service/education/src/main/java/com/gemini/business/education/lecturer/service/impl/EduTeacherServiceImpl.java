package com.gemini.business.education.lecturercation.lecturer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.education.lecturercation.lecturer.mapper.EduTeacherMapper;
import com.gemini.business.education.lecturercation.lecturer.po.EduTeacherPo;
import com.gemini.business.education.lecturercation.lecturer.service.EduTeacherService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 讲师
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:45:30 CST 2020
 */
@Service
public class EduTeacherServiceImpl extends BaseDetailServiceImpl<EduTeacherPo, EduTeacherPo, EduTeacherMapper, EduTeacherMapper> implements EduTeacherService {

    @Override
    public QueryWrapper<EduTeacherPo> wrapper(EduTeacherPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getName()), "name", po.getName())
                .eq(!StringUtils.isEmpty(po.getIntro()), "intro", po.getIntro())
                .eq(!StringUtils.isEmpty(po.getCareer()), "career", po.getCareer())
                .eq(!StringUtils.isEmpty(po.getLevel()), "level", po.getLevel())
                .eq(!StringUtils.isEmpty(po.getAvatar()), "avatar", po.getAvatar())
                .eq(!StringUtils.isEmpty(po.getSort()), "sort", po.getSort())
                .eq(!StringUtils.isEmpty(po.getIsDeleted()), "is_deleted", po.getIsDeleted())
                .eq(!StringUtils.isEmpty(po.getGmtCreate()), "gmt_create", po.getGmtCreate())
                .eq(!StringUtils.isEmpty(po.getGmtModified()), "gmt_modified", po.getGmtModified());
    }
}
