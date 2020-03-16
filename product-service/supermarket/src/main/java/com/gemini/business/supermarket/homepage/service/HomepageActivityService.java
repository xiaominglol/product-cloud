package com.gemini.business.supermarket.homepage.service;

import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.supermarket.homepage.mapper.HomepageActivityMapper;
import com.gemini.business.supermarket.homepage.po.HomepageActivityPo;

/**
 * 购物车表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
public interface HomepageActivityService extends BaseDetailService<HomepageActivityPo, HomepageActivityPo, HomepageActivityMapper, HomepageActivityMapper> {
}
