package com.gemini.business.supermarket.marketing.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.supermarket.marketing.mapper.MarketingCouponMapper;
import com.gemini.business.supermarket.marketing.po.MarketingCouponPo;
import com.gemini.business.supermarket.marketing.service.MarketingCouponService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 优惠劵表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Service
public class MarketingCouponServiceImpl extends BaseDetailServiceImpl<MarketingCouponPo, MarketingCouponPo, MarketingCouponMapper, MarketingCouponMapper> implements MarketingCouponService {

    @Override
    public QueryWrapper<MarketingCouponPo> wrapper(MarketingCouponPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getName()), "name", po.getName())
                .eq(!StringUtils.isEmpty(po.getDiscountAmount()), "discount_amount", po.getDiscountAmount());
    }
}
