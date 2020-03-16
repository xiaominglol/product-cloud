package com.gemini.business.supermarket.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.supermarket.order.mapper.OrderGoodsMapper;
import com.gemini.business.supermarket.order.po.OrderGoodsPo;
import com.gemini.business.supermarket.order.service.OrderGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 订单商品表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Service
public class OrderGoodsServiceImpl extends BaseDetailServiceImpl<OrderGoodsPo, OrderGoodsPo, OrderGoodsMapper, OrderGoodsMapper> implements OrderGoodsService {

    @Override
    public QueryWrapper<OrderGoodsPo> wrapper(OrderGoodsPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getOrderId()), "order_id", po.getOrderId())
                .eq(!StringUtils.isEmpty(po.getOrderNo()), "order_no", po.getOrderNo())
                .eq(!StringUtils.isEmpty(po.getGoodsId()), "goods_id", po.getGoodsId())
                .eq(!StringUtils.isEmpty(po.getGoodsName()), "goods_name", po.getGoodsName())
                .eq(!StringUtils.isEmpty(po.getUnitPrice()), "unit_price", po.getUnitPrice())
                .eq(!StringUtils.isEmpty(po.getQuantity()), "quantity", po.getQuantity())
                .eq(!StringUtils.isEmpty(po.getTotalPrice()), "total_price", po.getTotalPrice())
                .eq(!StringUtils.isEmpty(po.getDiscountAmount()), "discount_amount", po.getDiscountAmount())
                .eq(!StringUtils.isEmpty(po.getPayAmount()), "pay_amount", po.getPayAmount());
    }
}
