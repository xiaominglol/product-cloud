package com.gemini.business.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseServiceImpl;
import com.gemini.business.member.mapper.MemberAddressMapper;
import com.gemini.business.member.po.MemberAddressPo;
import com.gemini.business.member.service.MemberAddressService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 会员地址表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Service
public class MemberAddressServiceImpl extends BaseServiceImpl<MemberAddressPo, MemberAddressMapper> implements MemberAddressService {

    @Override
    public QueryWrapper<MemberAddressPo> wrapper(MemberAddressPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getMemberId()), "member_id", po.getMemberId())
                .eq(!StringUtils.isEmpty(po.getMemberNickname()), "member_nickname", po.getMemberNickname())
                .eq(!StringUtils.isEmpty(po.getName()), "name", po.getName())
                .eq(!StringUtils.isEmpty(po.getPhone()), "phone", po.getPhone())
                .eq(!StringUtils.isEmpty(po.getProvince()), "province", po.getProvince())
                .eq(!StringUtils.isEmpty(po.getCity()), "city", po.getCity())
                .eq(!StringUtils.isEmpty(po.getCounty()), "county", po.getCounty())
                .eq(!StringUtils.isEmpty(po.getDetailAddress()), "detail_address", po.getDetailAddress())
                .eq(!StringUtils.isEmpty(po.getIsDefault()), "is_default", po.getIsDefault());
    }
}
