package com.gemini.business.education.lecturercation.lecturer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.education.lecturercation.lecturer.mapper.EduCommentMapper;
import com.gemini.business.education.lecturercation.lecturer.po.EduCommentPo;
import com.gemini.business.education.lecturercation.lecturer.service.EduCommentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 评论
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:45:30 CST 2020
 */
@Service
public class EduCommentServiceImpl extends BaseDetailServiceImpl<EduCommentPo, EduCommentPo, EduCommentMapper, EduCommentMapper> implements EduCommentService {

    @Override
    public QueryWrapper<EduCommentPo> wrapper(EduCommentPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getCourseId()), "course_id", po.getCourseId())
                .eq(!StringUtils.isEmpty(po.getTeacherId()), "teacher_id", po.getTeacherId())
                .eq(!StringUtils.isEmpty(po.getMemberId()), "member_id", po.getMemberId())
                .eq(!StringUtils.isEmpty(po.getNickname()), "nickname", po.getNickname())
                .eq(!StringUtils.isEmpty(po.getAvatar()), "avatar", po.getAvatar())
                .eq(!StringUtils.isEmpty(po.getContent()), "content", po.getContent())
                .eq(!StringUtils.isEmpty(po.getIsDeleted()), "is_deleted", po.getIsDeleted())
                .eq(!StringUtils.isEmpty(po.getGmtCreate()), "gmt_create", po.getGmtCreate())
                .eq(!StringUtils.isEmpty(po.getGmtModified()), "gmt_modified", po.getGmtModified());
    }
}
