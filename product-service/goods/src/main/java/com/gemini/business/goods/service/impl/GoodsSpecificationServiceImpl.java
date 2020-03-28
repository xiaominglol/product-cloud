package com.gemini.business.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseServiceImpl;
import com.gemini.business.goods.mapper.GoodsSpecificationMapper;
import com.gemini.business.goods.po.GoodsSpecificationPo;
import com.gemini.business.goods.service.GoodsSpecificationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 商品分类规格表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Service
public class GoodsSpecificationServiceImpl extends BaseServiceImpl<GoodsSpecificationPo, GoodsSpecificationMapper> implements GoodsSpecificationService {

    @Override
    public QueryWrapper<GoodsSpecificationPo> wrapper(GoodsSpecificationPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getName()), "name", po.getName())
                .eq(!StringUtils.isEmpty(po.getCategoryId()), "category_id", po.getCategoryId())
                .eq(!StringUtils.isEmpty(po.getCategoryName()), "category_name", po.getCategoryName())
                .eq(!StringUtils.isEmpty(po.getValue()), "value", po.getValue())
                .eq(!StringUtils.isEmpty(po.getSort()), "sort", po.getSort())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getModifyUserId()), "modify_user_id", po.getModifyUserId())
                .eq(!StringUtils.isEmpty(po.getModifyUserName()), "modify_user_name", po.getModifyUserName())
                .eq(!StringUtils.isEmpty(po.getModifyTime()), "modify_time", po.getModifyTime());
    }
}
