package com.gemini.business.supermarket.platform.service;


import com.gemini.boot.framework.mybatis.service.BaseService;
import com.gemini.business.supermarket.platform.mapper.LoginLogMapper;
import com.gemini.business.supermarket.platform.po.LoginLogPo;

/**
 * 登陆日志表
 *
 * @author 小明不读书
 */
public interface LoginLogService extends BaseService<LoginLogPo, LoginLogMapper> {
}
