package com.gemini.business.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.admin.po.UserPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户表
 *
 * @author 小明不读书
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPo> {

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
    List<Map<String, String>> getUserRole(Long userId);

    /**
     * 删除用户角色
     *
     * @param account
     */
    void deleteUserRole(Long userId);
}
