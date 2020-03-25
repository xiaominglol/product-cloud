package com.gemini.business.admin.service;

import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.admin.mapper.DictMapper;
import com.gemini.business.admin.po.DictPo;

/**
 * 字典表
 *
 * @author 小明不读书
 */
public interface PlatformDictService extends BaseDetailService<DictPo, DictPo, DictMapper, DictMapper> {
}
