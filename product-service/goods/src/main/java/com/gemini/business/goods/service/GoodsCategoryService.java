package com.gemini.business.goods.service;

import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.goods.mapper.GoodsCategoryMapper;
import com.gemini.business.goods.po.GoodsCategoryPo;

/**
 * 商品分类表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
public interface GoodsCategoryService extends BaseDetailService<GoodsCategoryPo, GoodsCategoryPo, GoodsCategoryMapper, GoodsCategoryMapper> {
}
