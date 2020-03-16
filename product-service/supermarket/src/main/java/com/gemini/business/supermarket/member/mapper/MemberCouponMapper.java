package com.gemini.business.supermarket.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.supermarket.member.po.MemberCouponPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员优惠劵表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Mapper
public interface MemberCouponMapper extends BaseMapper<MemberCouponPo> {
}
