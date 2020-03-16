package com.gemini.business.supermarket.platform.service;


import com.gemini.boot.framework.mybatis.service.BaseService;
import com.gemini.business.supermarket.platform.mapper.OptLogMapper;
import com.gemini.business.supermarket.platform.po.OptLogPo;

/**
 * 操作日志表
 *
 * @author 小明不读书
 */
public interface OptLogService extends BaseService<OptLogPo, OptLogMapper> {
}
