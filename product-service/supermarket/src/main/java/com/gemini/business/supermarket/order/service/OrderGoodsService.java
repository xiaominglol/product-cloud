package com.gemini.business.supermarket.order.service;

import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.supermarket.order.mapper.OrderGoodsMapper;
import com.gemini.business.supermarket.order.po.OrderGoodsPo;

/**
 * 订单商品表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
public interface OrderGoodsService extends BaseDetailService<OrderGoodsPo, OrderGoodsPo, OrderGoodsMapper, OrderGoodsMapper> {
}
