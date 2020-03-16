package com.gemini.business.supermarket.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.supermarket.order.mapper.OrderMapper;
import com.gemini.business.supermarket.order.po.OrderPo;
import com.gemini.business.supermarket.order.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 订单表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Service
public class OrderServiceImpl extends BaseDetailServiceImpl<OrderPo, OrderPo, OrderMapper, OrderMapper> implements OrderService {

    @Override
    public QueryWrapper<OrderPo> wrapper(OrderPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getOrderNo()), "order_no", po.getOrderNo())
                .eq(!StringUtils.isEmpty(po.getMemberId()), "member_id", po.getMemberId())
                .eq(!StringUtils.isEmpty(po.getMemberNickname()), "member_nickname", po.getMemberNickname())
                .eq(!StringUtils.isEmpty(po.getCouponId()), "coupon_id", po.getCouponId())
                .eq(!StringUtils.isEmpty(po.getCouponName()), "coupon_name", po.getCouponName())
                .eq(!StringUtils.isEmpty(po.getTotalPrice()), "total_price", po.getTotalPrice())
                .eq(!StringUtils.isEmpty(po.getDiscountAmount()), "discount_amount", po.getDiscountAmount())
                .eq(!StringUtils.isEmpty(po.getPayAmount()), "pay_amount", po.getPayAmount())
                .eq(!StringUtils.isEmpty(po.getReceiverName()), "receiver_name", po.getReceiverName())
                .eq(!StringUtils.isEmpty(po.getReceiverPhone()), "receiver_phone", po.getReceiverPhone())
                .eq(!StringUtils.isEmpty(po.getReceiverProvince()), "receiver_province", po.getReceiverProvince())
                .eq(!StringUtils.isEmpty(po.getReceiverCity()), "receiver_city", po.getReceiverCity())
                .eq(!StringUtils.isEmpty(po.getReceiverCounty()), "receiver_county", po.getReceiverCounty())
                .eq(!StringUtils.isEmpty(po.getReceiverDetailAddress()), "receiver_detail_address", po.getReceiverDetailAddress())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getCreateTime()), "create_time", po.getCreateTime())
                .eq(!StringUtils.isEmpty(po.getPaymentTime()), "payment_time", po.getPaymentTime())
                .eq(!StringUtils.isEmpty(po.getReceiveTime()), "receive_time", po.getReceiveTime());
    }
}
