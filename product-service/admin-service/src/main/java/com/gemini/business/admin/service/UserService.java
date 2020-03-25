package com.gemini.business.admin.service;


import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.admin.mapper.UserMapper;
import com.gemini.business.admin.mapper.UserRoleMapper;
import com.gemini.business.admin.po.UserPo;
import com.gemini.business.admin.po.UserRolePo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户表
 *
 * @author 小明不读书
 */
public interface UserService extends BaseDetailService<UserPo, UserRolePo, UserMapper, UserRoleMapper> {

    /**
     * 根据account查询用户
     *
     * @param account
     * @return
     */
    UserPo getByAccount(String account);

    /**
     * 根据account查询用户角色
     *
     * @param account
     * @return
     */
    Set<String> getRoleById(Long userId);

    /**
     * 根据account查询用户权限
     *
     * @param account
     * @return
     */
    Set<String> getPermissionsById(Long userId);

    /**
     * 通过用户账号查询用户角色
     *
     * @param account
     * @return
     */
    List<Map<String, String>> getUserRole(Long account);
}
