package com.gemini.business.supermarket.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.supermarket.member.po.MemberGradePo;
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
