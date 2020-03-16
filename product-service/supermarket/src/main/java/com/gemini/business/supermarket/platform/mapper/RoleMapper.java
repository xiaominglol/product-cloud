package com.gemini.business.supermarket.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.supermarket.platform.po.RolePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 *
 * @author 小明不读书
 */
@Mapper
public interface RoleMapper extends BaseMapper<RolePo> {

    /**
     * 通过角色id查询权限
     *
     * @param id 主键id
     * @return
     */
    List<Map<String, String>> getAut(@Param("id") Long id);

    /**
     * 添加权限
     *
     * @param id
     * @param ids
     */
    void addAut(@Param("id") Long id, @Param("ids") Long[] ids);

    /**
     * 删除权限
     *
     * @param id
     */
    void deleteRoleMenu(@Param("id") Long id);
}
