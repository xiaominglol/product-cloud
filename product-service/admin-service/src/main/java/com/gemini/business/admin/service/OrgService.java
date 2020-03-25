package com.gemini.business.admin.service;


import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.admin.mapper.OrgMapper;
import com.gemini.business.admin.po.OrgPo;

/**
 * 组织架构表
 *
 * @author 小明不读书
 */
public interface OrgService extends BaseDetailService<OrgPo, OrgPo, OrgMapper, OrgMapper> {
}
