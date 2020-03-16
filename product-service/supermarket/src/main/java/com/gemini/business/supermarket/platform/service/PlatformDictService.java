package com.gemini.business.supermarket.platform.service;

import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.supermarket.platform.mapper.DictMapper;
import com.gemini.business.supermarket.platform.po.DictPo;

/**
 * 字典表
 *
 * @author 小明不读书
 */
public interface PlatformDictService extends BaseDetailService<DictPo, DictPo, DictMapper, DictMapper> {
}
