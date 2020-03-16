package com.gemini.business.supermarket.platform.service;


import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.supermarket.platform.mapper.MenuMapper;
import com.gemini.business.supermarket.platform.po.MenuPo;

import java.util.List;

/**
 * 菜单表
 *
 * @author 小明不读书
 */
public interface MenuService extends BaseDetailService<MenuPo, MenuPo, MenuMapper, MenuMapper> {

    /**
     * 通过用户ID查询所有列表（不带分页）
     *
     * @param userId
     * @return
     */
    List<MenuPo> getByUserId(Long userId);
}
