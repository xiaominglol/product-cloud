package com.gemini.business.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.admin.po.LoginLogPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登陆日志表
 *
 * @author 小明不读书
 */
@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLogPo> {
}
