package com.gemini.business.admin.service;


import com.gemini.boot.framework.mybatis.service.BaseService;
import com.gemini.business.admin.mapper.OptLogMapper;
import com.gemini.business.admin.po.OptLogPo;

/**
 * 操作日志表
 *
 * @author 小明不读书
 */
public interface OptLogService extends BaseService<OptLogPo, OptLogMapper> {
}
