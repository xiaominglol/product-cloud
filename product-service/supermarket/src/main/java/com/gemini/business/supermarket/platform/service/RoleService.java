package com.gemini.business.supermarket.platform.service;


import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.supermarket.platform.mapper.RoleMapper;
import com.gemini.business.supermarket.platform.mapper.RoleMenuMapper;
import com.gemini.business.supermarket.platform.po.RoleMenuPo;
import com.gemini.business.supermarket.platform.po.RolePo;

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
