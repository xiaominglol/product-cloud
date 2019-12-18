package com.gemini.business.service;

import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.mapper.GoodsMapper;
import com.gemini.business.mapper.GoodsStockMapper;
import com.gemini.business.po.GoodsPo;
import com.gemini.business.po.GoodsStockPo;

/**
 * 商品表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
public interface GoodsService extends BaseDetailService<GoodsPo, GoodsStockPo, GoodsMapper, GoodsStockMapper> {
}