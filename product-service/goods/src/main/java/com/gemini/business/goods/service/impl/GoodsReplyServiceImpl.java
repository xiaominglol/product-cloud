package com.gemini.business.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseServiceImpl;
import com.gemini.business.goods.mapper.GoodsReplyMapper;
import com.gemini.business.goods.po.GoodsReplyPo;
import com.gemini.business.goods.service.GoodsReplyService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 商品评论回复表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Service
public class GoodsReplyServiceImpl extends BaseServiceImpl<GoodsReplyPo, GoodsReplyMapper> implements GoodsReplyService {

    @Override
    public QueryWrapper<GoodsReplyPo> wrapper(GoodsReplyPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getName()), "name", po.getName())
                .eq(!StringUtils.isEmpty(po.getBrandId()), "brand_id", po.getBrandId())
                .eq(!StringUtils.isEmpty(po.getBrandName()), "brand_name", po.getBrandName())
                .eq(!StringUtils.isEmpty(po.getClassificationId()), "classification_id", po.getClassificationId())
                .eq(!StringUtils.isEmpty(po.getClassificationName()), "classification_name", po.getClassificationName())
                .eq(!StringUtils.isEmpty(po.getStockId()), "stock_id", po.getStockId())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getModifyUserId()), "modify_user_id", po.getModifyUserId())
                .eq(!StringUtils.isEmpty(po.getModifyUserName()), "modify_user_name", po.getModifyUserName())
                .eq(!StringUtils.isEmpty(po.getModifyTime()), "modify_time", po.getModifyTime());
    }
}
