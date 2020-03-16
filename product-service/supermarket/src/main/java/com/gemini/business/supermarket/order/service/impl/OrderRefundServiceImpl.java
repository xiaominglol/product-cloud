package com.gemini.business.supermarket.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.supermarket.order.mapper.OrderRefundMapper;
import com.gemini.business.supermarket.order.po.OrderRefundPo;
import com.gemini.business.supermarket.order.service.OrderRefundService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 订单退款表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Service
public class OrderRefundServiceImpl extends BaseDetailServiceImpl<OrderRefundPo, OrderRefundPo, OrderRefundMapper, OrderRefundMapper> implements OrderRefundService {

    @Override
    public QueryWrapper<OrderRefundPo> wrapper(OrderRefundPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getOrderId()), "order_id", po.getOrderId())
                .eq(!StringUtils.isEmpty(po.getOrderNo()), "order_no", po.getOrderNo())
                .eq(!StringUtils.isEmpty(po.getOrderGoodsId()), "order_goods_id", po.getOrderGoodsId())
                .eq(!StringUtils.isEmpty(po.getMemberId()), "member_id", po.getMemberId())
                .eq(!StringUtils.isEmpty(po.getMemberNickname()), "member_nickname", po.getMemberNickname())
                .eq(!StringUtils.isEmpty(po.getUnitPrice()), "unit_price", po.getUnitPrice())
                .eq(!StringUtils.isEmpty(po.getQuantity()), "quantity", po.getQuantity())
                .eq(!StringUtils.isEmpty(po.getTotalPrice()), "total_price", po.getTotalPrice())
                .eq(!StringUtils.isEmpty(po.getDiscountAmount()), "discount_amount", po.getDiscountAmount())
                .eq(!StringUtils.isEmpty(po.getRefundAmount()), "refund_amount", po.getRefundAmount())
                .eq(!StringUtils.isEmpty(po.getRefundReason()), "refund_reason", po.getRefundReason())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getRefundTime()), "refund_time", po.getRefundTime());
    }
}
