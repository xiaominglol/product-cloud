package com.gemini.business.member.service;

import com.gemini.boot.framework.mybatis.service.BaseService;
import com.gemini.business.member.mapper.MemberAddressMapper;
import com.gemini.business.member.po.MemberAddressPo;

/**
 * 会员地址表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
public interface MemberAddressService extends BaseService<MemberAddressPo, MemberAddressMapper> {
}
