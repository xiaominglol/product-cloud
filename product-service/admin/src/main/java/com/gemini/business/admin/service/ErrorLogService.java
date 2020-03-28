package com.gemini.business.admin.service;


import com.gemini.boot.framework.mybatis.service.BaseService;
import com.gemini.business.admin.mapper.ErrorLogMapper;
import com.gemini.business.admin.po.ErrorLogPo;

/**
 * 错误日志表
 *
 * @author 小明不读书
 */
public interface ErrorLogService extends BaseService<ErrorLogPo, ErrorLogMapper> {
}
