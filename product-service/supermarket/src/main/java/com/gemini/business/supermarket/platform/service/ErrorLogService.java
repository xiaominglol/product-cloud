package com.gemini.business.supermarket.platform.service;


import com.gemini.boot.framework.mybatis.service.BaseService;
import com.gemini.business.supermarket.platform.mapper.ErrorLogMapper;
import com.gemini.business.supermarket.platform.po.ErrorLogPo;

/**
 * 错误日志表
 *
 * @author 小明不读书
 */
public interface ErrorLogService extends BaseService<ErrorLogPo, ErrorLogMapper> {
}
