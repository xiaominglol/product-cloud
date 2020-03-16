package com.gemini.business.supermarket.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.supermarket.platform.po.MenuPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单表
 *
 * @author 小明不读书
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuPo> {

    /**
     * 通过用户ID查询所有列表（不带分页）
     *
     * @param userId
     * @return
     */
    List<MenuPo> getByUserId(Long userId);

    /**
     * 删除菜单权限
     *
     * @param id
     */
    void deleteMenuAut(Long id);
}
