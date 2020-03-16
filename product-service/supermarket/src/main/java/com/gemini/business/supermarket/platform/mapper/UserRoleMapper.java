package com.gemini.business.supermarket.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.supermarket.platform.po.UserRolePo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色
 *
 * @author 小明不读书
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRolePo> {
}
