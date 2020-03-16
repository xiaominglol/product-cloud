package com.gemini.business.supermarket.member.service;

import com.gemini.boot.framework.mybatis.service.BaseService;
import com.gemini.business.supermarket.member.mapper.MemberGradeMapper;
import com.gemini.business.supermarket.member.po.MemberGradePo;

/**
 * 会员等级表
 *
 * @author 小明不读书
 * @date Fri Jan 03 15:04:36 CST 2020
 */
public interface MemberGradeService extends BaseService<MemberGradePo, MemberGradeMapper> {
}
