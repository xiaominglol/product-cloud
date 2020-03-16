package com.gemini.business.supermarket.shopping.service;

import com.gemini.boot.framework.mybatis.service.BaseService;
import com.gemini.business.supermarket.shopping.mapper.ShoppingCartMapper;
import com.gemini.business.supermarket.shopping.po.ShoppingCartPo;

/**
 * 购物车表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
public interface ShoppingCartService extends BaseService<ShoppingCartPo, ShoppingCartMapper> {
}
