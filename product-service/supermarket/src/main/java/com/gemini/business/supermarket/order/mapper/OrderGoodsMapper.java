package com.gemini.business.supermarket.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.supermarket.order.po.OrderGoodsPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单商品表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Mapper
public interface OrderGoodsMapper extends BaseMapper<OrderGoodsPo> {
}
