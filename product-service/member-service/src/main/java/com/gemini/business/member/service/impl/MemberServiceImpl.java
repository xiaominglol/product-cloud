package com.gemini.business.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseServiceImpl;
import com.gemini.business.member.mapper.MemberMapper;
import com.gemini.business.member.po.MemberPo;
import com.gemini.business.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 会员表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberPo, MemberMapper> implements MemberService {

    @Override
    public QueryWrapper<MemberPo> wrapper(MemberPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getNickname()), "nickname", po.getNickname())
                .eq(!StringUtils.isEmpty(po.getPhone()), "phone", po.getPhone())
                .eq(!StringUtils.isEmpty(po.getPassword()), "password", po.getPassword())
                .eq(!StringUtils.isEmpty(po.getRealName()), "real_name", po.getRealName())
                .eq(!StringUtils.isEmpty(po.getIdCardNo()), "id_card_no", po.getIdCardNo())
                .eq(!StringUtils.isEmpty(po.getGradeCode()), "grade_code", po.getGradeCode())
                .eq(!StringUtils.isEmpty(po.getGradeName()), "grade_name", po.getGradeName())
                .eq(!StringUtils.isEmpty(po.getCreateTime()), "create_time", po.getCreateTime())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName());
    }
}
