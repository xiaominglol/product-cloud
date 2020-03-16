package com.gemini.business.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseServiceImpl;
import com.gemini.business.member.mapper.MemberIntegralMapper;
import com.gemini.business.member.po.MemberIntegralPo;
import com.gemini.business.member.service.MemberIntegralService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 会员积分表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Service
public class MemberIntegralServiceImpl extends BaseServiceImpl<MemberIntegralPo, MemberIntegralMapper> implements MemberIntegralService {

    @Override
    public QueryWrapper<MemberIntegralPo> wrapper(MemberIntegralPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getMemberId()), "member_id", po.getMemberId())
                .eq(!StringUtils.isEmpty(po.getMemberNickname()), "member_nickname", po.getMemberNickname())
                .eq(!StringUtils.isEmpty(po.getIntegral()), "integral", po.getIntegral());
    }
}
