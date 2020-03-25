package com.gemini.business.admin.service;


import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.admin.mapper.RoleMapper;
import com.gemini.business.admin.mapper.RoleMenuMapper;
import com.gemini.business.admin.po.RoleMenuPo;
import com.gemini.business.admin.po.RolePo;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 *
 * @author 小明不读书
 */
public interface RoleService extends BaseDetailService<RolePo, RoleMenuPo, RoleMapper, RoleMenuMapper> {

    /**
     * 通过角色id查询权限
     *
     * @param id
     */
    List<Map<String, String>> getAut(Long id);
}
