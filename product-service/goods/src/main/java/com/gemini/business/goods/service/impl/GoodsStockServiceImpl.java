package com.gemini.business.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseServiceImpl;
import com.gemini.business.goods.mapper.GoodsStockMapper;
import com.gemini.business.goods.po.GoodsStockPo;
import com.gemini.business.goods.service.GoodsStockService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 商品库存表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Service
public class GoodsStockServiceImpl extends BaseServiceImpl<GoodsStockPo, GoodsStockMapper> implements GoodsStockService {

    @Override
    public QueryWrapper<GoodsStockPo> wrapper(GoodsStockPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getSpecificationName()), "specification_name", po.getSpecificationName())
                .eq(!StringUtils.isEmpty(po.getGoodsId()), "goods_id", po.getGoodsId())
                .eq(!StringUtils.isEmpty(po.getGoodsName()), "goods_name", po.getGoodsName())
                .eq(!StringUtils.isEmpty(po.getOriginalPrice()), "original_price", po.getOriginalPrice())
                .eq(!StringUtils.isEmpty(po.getCurrentPrice()), "current_price", po.getCurrentPrice())
                .eq(!StringUtils.isEmpty(po.getSurplusStock()), "surplus_stock", po.getSurplusStock())
                .eq(!StringUtils.isEmpty(po.getUsedStock()), "used_stock", po.getUsedStock())
                .eq(!StringUtils.isEmpty(po.getLockStock()), "lock_stock", po.getLockStock())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getModifyUserId()), "modify_user_id", po.getModifyUserId())
                .eq(!StringUtils.isEmpty(po.getModifyUserName()), "modify_user_name", po.getModifyUserName())
                .eq(!StringUtils.isEmpty(po.getModifyTime()), "modify_time", po.getModifyTime());
    }
}
