package com.gemini.business.supermarket.marketing.service;

import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.supermarket.marketing.mapper.MarketingCouponMapper;
import com.gemini.business.supermarket.marketing.po.MarketingCouponPo;

/**
 * 优惠劵表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
public interface MarketingCouponService extends BaseDetailService<MarketingCouponPo, MarketingCouponPo, MarketingCouponMapper, MarketingCouponMapper> {
}
