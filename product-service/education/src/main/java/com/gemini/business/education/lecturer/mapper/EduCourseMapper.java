package com.gemini.business.education.lecturercation.lecturer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.education.lecturercation.lecturer.po.EduCoursePo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程
 *
 * @author 小明不读书
 * @date Fri Mar 27 20:45:30 CST 2020
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCoursePo> {
}
