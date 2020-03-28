package com.gemini.business.admin.service;


import com.gemini.boot.framework.mybatis.service.BaseService;
import com.gemini.business.admin.mapper.LoginLogMapper;
import com.gemini.business.admin.po.LoginLogPo;

/**
 * 登陆日志表
 *
 * @author 小明不读书
 */
public interface LoginLogService extends BaseService<LoginLogPo, LoginLogMapper> {
}
