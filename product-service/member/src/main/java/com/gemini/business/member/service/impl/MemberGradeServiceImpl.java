package com.gemini.business.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseServiceImpl;
import com.gemini.business.member.mapper.MemberGradeMapper;
import com.gemini.business.member.po.MemberGradePo;
import com.gemini.business.member.service.MemberGradeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 会员等级表
 *
 * @author 小明不读书
 * @date Fri Jan 03 15:04:36 CST 2020
 */
@Service
public class MemberGradeServiceImpl extends BaseServiceImpl<MemberGradePo, MemberGradeMapper> implements MemberGradeService {

    @Override
    public QueryWrapper<MemberGradePo> wrapper(MemberGradePo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getCode()), "code", po.getCode())
                .eq(!StringUtils.isEmpty(po.getName()), "name", po.getName());
    }
}
