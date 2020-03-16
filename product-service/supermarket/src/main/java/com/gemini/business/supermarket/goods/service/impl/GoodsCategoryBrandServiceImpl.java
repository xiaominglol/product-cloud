package com.gemini.business.supermarket.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseServiceImpl;
import com.gemini.business.supermarket.goods.mapper.GoodsCategoryBrandMapper;
import com.gemini.business.supermarket.goods.po.GoodsCategoryBrandPo;
import com.gemini.business.supermarket.goods.service.GoodsCategoryBrandService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 商品分类品牌表
 *
 * @author 小明不读书
 * @date Tue Jan 21 14:55:00 CST 2020
 */
@Service
public class GoodsCategoryBrandServiceImpl extends BaseServiceImpl<GoodsCategoryBrandPo, GoodsCategoryBrandMapper> implements GoodsCategoryBrandService {

    @Override
    public QueryWrapper<GoodsCategoryBrandPo> wrapper(GoodsCategoryBrandPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getBrandId()), "brand_id", po.getBrandId())
                .eq(!StringUtils.isEmpty(po.getBrandName()), "brand_name", po.getBrandName())
                .eq(!StringUtils.isEmpty(po.getCategoryId()), "category_id", po.getCategoryId())
                .eq(!StringUtils.isEmpty(po.getCategoryName()), "category_name", po.getCategoryName())
                .eq(!StringUtils.isEmpty(po.getLogo()), "logo", po.getLogo())
                .eq(!StringUtils.isEmpty(po.getSort()), "sort", po.getSort())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getModifyUserId()), "modify_user_id", po.getModifyUserId())
                .eq(!StringUtils.isEmpty(po.getModifyUserName()), "modify_user_name", po.getModifyUserName())
                .eq(!StringUtils.isEmpty(po.getModifyTime()), "modify_time", po.getModifyTime());
    }
}
