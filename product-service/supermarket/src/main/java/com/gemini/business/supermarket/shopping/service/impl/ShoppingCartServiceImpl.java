package com.gemini.business.supermarket.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseServiceImpl;
import com.gemini.business.supermarket.shopping.mapper.ShoppingCartMapper;
import com.gemini.business.supermarket.shopping.po.ShoppingCartPo;
import com.gemini.business.supermarket.shopping.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 购物车表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Service
public class ShoppingCartServiceImpl extends BaseServiceImpl<ShoppingCartPo, ShoppingCartMapper> implements ShoppingCartService {

    @Override
    public QueryWrapper<ShoppingCartPo> wrapper(ShoppingCartPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getMemberId()), "member_id", po.getMemberId())
                .eq(!StringUtils.isEmpty(po.getMemberNickname()), "member_nickname", po.getMemberNickname())
                .eq(!StringUtils.isEmpty(po.getGoodsId()), "goods_id", po.getGoodsId())
                .eq(!StringUtils.isEmpty(po.getGoodsName()), "goods_name", po.getGoodsName())
                .eq(!StringUtils.isEmpty(po.getUnitPrice()), "unit_price", po.getUnitPrice())
                .eq(!StringUtils.isEmpty(po.getQuantity()), "quantity", po.getQuantity())
                .eq(!StringUtils.isEmpty(po.getTotalPrice()), "total_price", po.getTotalPrice())
                .eq(!StringUtils.isEmpty(po.getCreateTime()), "create_time", po.getCreateTime());
    }
}
