package com.gemini.business.supermarket.homepage.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BaseDetailPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("homepage_activity")
public class HomepageActivityPo extends BaseDetailPo<HomepageActivityPo> {

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 会员昵称
     */
    private String memberNickname;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 数量
     */
    private Long quantity;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 创建时间
     */
    private Date createTime;
}
