package com.gemini.business.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseServiceImpl;
import com.gemini.business.member.mapper.MemberCouponMapper;
import com.gemini.business.member.po.MemberCouponPo;
import com.gemini.business.member.service.MemberCouponService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 会员优惠劵表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Service
public class MemberCouponServiceImpl extends BaseServiceImpl<MemberCouponPo, MemberCouponMapper> implements MemberCouponService {

    @Override
    public QueryWrapper<MemberCouponPo> wrapper(MemberCouponPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getMemberId()), "member_id", po.getMemberId())
                .eq(!StringUtils.isEmpty(po.getMemberNickname()), "member_nickname", po.getMemberNickname())
                .eq(!StringUtils.isEmpty(po.getCouponId()), "coupon_id", po.getCouponId())
                .eq(!StringUtils.isEmpty(po.getCouponName()), "coupon_name", po.getCouponName())
                .eq(!StringUtils.isEmpty(po.getCreateTime()), "create_time", po.getCreateTime())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName());
    }
}
