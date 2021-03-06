package com.gemini.business.goods.service;

import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.goods.mapper.GoodsParamMapper;
import com.gemini.business.goods.po.GoodsParamPo;

/**
 * 商品参数表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
public interface GoodsParamService extends BaseDetailService<GoodsParamPo, GoodsParamPo, GoodsParamMapper, GoodsParamMapper> {
}
