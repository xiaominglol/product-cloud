package com.gemini.business.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.boot.framework.mybatis.utils.BeanUtils;
import com.gemini.business.admin.mapper.UserMapper;
import com.gemini.business.admin.mapper.UserRoleMapper;
import com.gemini.business.admin.po.UserPo;
import com.gemini.business.admin.po.UserRolePo;
import com.gemini.business.admin.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 用户表
 *
 * @author 小明不读书
 */
@Service
public class UserServiceImpl extends BaseDetailServiceImpl<UserPo, UserRolePo, UserMapper, UserRoleMapper> implements UserService {

    @Override
    public QueryWrapper<UserPo> wrapper(UserPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getAccount()), "account", po.getAccount())
                .eq(!StringUtils.isEmpty(po.getName()), "name", po.getName())
                .eq(!StringUtils.isEmpty(po.getPassword()), "password", po.getPassword())
                .eq(!StringUtils.isEmpty(po.getPicture()), "picture", po.getPicture())
                .eq(!StringUtils.isEmpty(po.getOrgId()), "org_id", po.getOrgId())
                .eq(!StringUtils.isEmpty(po.getOrgName()), "org_name", po.getOrgName())
//                .eq(!StringUtils.isEmpty(po.getCreateDatetime()), "create_time", po.getCreateDatetime())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getModifyUserId()), "modify_user_id", po.getModifyUserId())
                .eq(!StringUtils.isEmpty(po.getModifyUserName()), "modify_user_name", po.getModifyUserName())
                .eq(!StringUtils.isEmpty(po.getModifyTime()), "modify_time", po.getModifyTime());
    }

    @Override
    public UserPo getByAccount(String account) {
        QueryWrapper<UserPo> qw = new QueryWrapper<>();
        qw.eq("account", account);
        return mapper.selectOne(qw);
    }

    @Override
    public Set<String> getRoleById(Long userId) {
        return mapper.getRoleById(userId);
    }

    @Override
    public Set<String> getPermissionsById(Long userId) {
        return mapper.getPermissionsById(userId);
    }

    @Override
    public List<Map<String, String>> getUserRole(Long account) {
        return mapper.getUserRole(account);
    }


    @Override
    public void insertAfter(UserPo po, List<UserRolePo> detailPos, Boolean isBase) {
        if (null != detailPos && 0 < detailPos.size()) {
            for (UserRolePo detailPo : detailPos) {
                if (isBase) {
                    insertDetailBefore(detailPo);
                }
                BeanUtils.invoke(detailPo, "setUserId", BeanUtils.invoke(po, "getId"));
                detailMapper().insert(detailPo);
            }
        }
    }

    @Override
    public void deleteBefore(Long id) {
        //删除角色权限
        mapper.deleteUserRole(id);
    }
}
