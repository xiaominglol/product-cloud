package com.gemini.business.supermarket.platform.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.supermarket.platform.mapper.OrgMapper;
import com.gemini.business.supermarket.platform.po.OrgPo;
import com.gemini.business.supermarket.platform.service.OrgService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 组织架构表
 *
 * @author 小明不读书
 */
@Service
public class OrgServiceImpl extends BaseDetailServiceImpl<OrgPo, OrgPo, OrgMapper, OrgMapper> implements OrgService {

    @Override
    public QueryWrapper<OrgPo> wrapper(OrgPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getPid()), "pid", po.getPid())
                .eq(!StringUtils.isEmpty(po.getName()), "name", po.getName())
                .eq(!StringUtils.isEmpty(po.getOrgTypeId()), "org_type_id", po.getOrgTypeId())
                .eq(!StringUtils.isEmpty(po.getOrgTypeCode()), "org_type_code", po.getOrgTypeCode())
                .eq(!StringUtils.isEmpty(po.getOrgTypeName()), "org_type_name", po.getOrgTypeName())
                .eq(!StringUtils.isEmpty(po.getSort()), "sort", po.getSort())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getModifyUserId()), "modify_user_id", po.getModifyUserId())
                .eq(!StringUtils.isEmpty(po.getModifyUserName()), "modify_user_name", po.getModifyUserName())
                .eq(!StringUtils.isEmpty(po.getModifyTime()), "modify_time", po.getModifyTime());
    }
}
