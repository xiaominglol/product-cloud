package com.gemini.business.supermarket.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.supermarket.order.mapper.OrderHistoryMapper;
import com.gemini.business.supermarket.order.po.OrderHistoryPo;
import com.gemini.business.supermarket.order.service.OrderHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Service
public class OrderHistoryServiceImpl extends BaseDetailServiceImpl<OrderHistoryPo, OrderHistoryPo, OrderHistoryMapper, OrderHistoryMapper> implements OrderHistoryService {

    @Override
    public QueryWrapper<OrderHistoryPo> wrapper(OrderHistoryPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getOrderId()), "order_id", po.getOrderId())
                .eq(!StringUtils.isEmpty(po.getOrderNo()), "order_no", po.getOrderNo());
    }
}
