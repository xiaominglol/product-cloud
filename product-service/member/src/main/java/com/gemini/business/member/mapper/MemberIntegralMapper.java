package com.gemini.business.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.member.po.MemberIntegralPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员积分表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Mapper
public interface MemberIntegralMapper extends BaseMapper<MemberIntegralPo> {
}
