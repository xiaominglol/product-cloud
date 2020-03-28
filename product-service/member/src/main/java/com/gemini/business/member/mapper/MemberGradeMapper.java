package com.gemini.business.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.member.po.MemberGradePo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员等级表
 *
 * @author 小明不读书
 * @date Fri Jan 03 15:04:36 CST 2020
 */
@Mapper
public interface MemberGradeMapper extends BaseMapper<MemberGradePo> {
}
