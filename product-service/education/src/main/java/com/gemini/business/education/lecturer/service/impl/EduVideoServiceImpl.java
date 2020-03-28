package com.gemini.business.education.lecturercation.lecturer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.education.lecturercation.lecturer.mapper.EduVideoMapper;
import com.gemini.business.education.lecturercation.lecturer.po.EduVideoPo;
import com.gemini.business.education.lecturercation.lecturer.service.EduVideoService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 课程视频
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:45:30 CST 2020
 */
@Service
public class EduVideoServiceImpl extends BaseDetailServiceImpl<EduVideoPo, EduVideoPo, EduVideoMapper, EduVideoMapper> implements EduVideoService {

    @Override
    public QueryWrapper<EduVideoPo> wrapper(EduVideoPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getCourseId()), "course_id", po.getCourseId())
                .eq(!StringUtils.isEmpty(po.getChapterId()), "chapter_id", po.getChapterId())
                .eq(!StringUtils.isEmpty(po.getTitle()), "title", po.getTitle())
                .eq(!StringUtils.isEmpty(po.getVideoSourceId()), "video_source_id", po.getVideoSourceId())
                .eq(!StringUtils.isEmpty(po.getVideoOriginalName()), "video_original_name", po.getVideoOriginalName())
                .eq(!StringUtils.isEmpty(po.getSort()), "sort", po.getSort())
                .eq(!StringUtils.isEmpty(po.getPlayCount()), "play_count", po.getPlayCount())
                .eq(!StringUtils.isEmpty(po.getIsFree()), "is_free", po.getIsFree())
                .eq(!StringUtils.isEmpty(po.getDuration()), "duration", po.getDuration())
                .eq(!StringUtils.isEmpty(po.getStatus()), "status", po.getStatus())
                .eq(!StringUtils.isEmpty(po.getSize()), "size", po.getSize())
                .eq(!StringUtils.isEmpty(po.getVersion()), "version", po.getVersion())
                .eq(!StringUtils.isEmpty(po.getGmtCreate()), "gmt_create", po.getGmtCreate())
                .eq(!StringUtils.isEmpty(po.getGmtModified()), "gmt_modified", po.getGmtModified());
    }
}
