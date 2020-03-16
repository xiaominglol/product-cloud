package com.gemini.business.supermarket.order.service;

import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.supermarket.order.mapper.OrderRefundMapper;
import com.gemini.business.supermarket.order.po.OrderRefundPo;

/**
 * 订单退款表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
public interface OrderRefundService extends BaseDetailService<OrderRefundPo, OrderRefundPo, OrderRefundMapper, OrderRefundMapper> {
}
