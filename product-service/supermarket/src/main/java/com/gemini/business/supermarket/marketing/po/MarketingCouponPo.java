package com.gemini.business.supermarket.marketing.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BaseDetailPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 优惠劵表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("marketing_coupon")
public class MarketingCouponPo extends BaseDetailPo<MarketingCouponPo> {

    /**
     * 名称
     */
    private String name;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;
}
