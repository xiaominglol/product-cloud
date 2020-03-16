package com.gemini.business.supermarket.platform.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.boot.framework.mybatis.utils.BeanUtils;
import com.gemini.business.supermarket.platform.mapper.RoleMapper;
import com.gemini.business.supermarket.platform.mapper.RoleMenuMapper;
import com.gemini.business.supermarket.platform.po.RoleMenuPo;
import com.gemini.business.supermarket.platform.po.RolePo;
import com.gemini.business.supermarket.platform.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


/**
 * 角色表
 *
 * @author 小明不读书
 */
@Service
public class RoleServiceImpl extends BaseDetailServiceImpl<RolePo, RoleMenuPo, RoleMapper, RoleMenuMapper> implements RoleService {

    @Override
    public QueryWrapper<RolePo> wrapper(RolePo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getCode()), "code", po.getCode())
                .eq(!StringUtils.isEmpty(po.getName()), "name", po.getName())
                .eq(!StringUtils.isEmpty(po.getSort()), "sort", po.getSort())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getModifyUserId()), "modify_user_id", po.getModifyUserId())
                .eq(!StringUtils.isEmpty(po.getModifyUserName()), "modify_user_name", po.getModifyUserName())
                .eq(!StringUtils.isEmpty(po.getModifyTime()), "modify_time", po.getModifyTime());
    }

    @Override
    public List<Map<String, String>> getAut(Long id) {
        return mapper.getAut(id);
    }

    @Override
    public void insertAfter(RolePo po, List<RoleMenuPo> detailPos, Boolean isBase) {
        if (null != detailPos && 0 < detailPos.size()) {
            for (RoleMenuPo detailPo : detailPos) {
                if (isBase) {
                    insertDetailBefore(detailPo);
                }
                BeanUtils.invoke(detailPo, "setRoleId", BeanUtils.invoke(po, "getId"));
                detailMapper().insert(detailPo);
            }
        }
    }

    @Override
    public void deleteBefore(Long id) {
        //删除角色权限
        mapper.deleteRoleMenu(id);
    }
}
