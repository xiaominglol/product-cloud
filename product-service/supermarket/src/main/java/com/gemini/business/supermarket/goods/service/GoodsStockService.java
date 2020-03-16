package com.gemini.business.supermarket.goods.service;

import com.gemini.boot.framework.mybatis.service.BaseService;
import com.gemini.business.supermarket.goods.mapper.GoodsStockMapper;
import com.gemini.business.supermarket.goods.po.GoodsStockPo;

/**
 * 商品库存表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
public interface GoodsStockService extends BaseService<GoodsStockPo, GoodsStockMapper> {
}
