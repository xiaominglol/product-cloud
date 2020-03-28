package com.gemini.business.goods.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BasePo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品库存表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Data
@TableName("goods_stock")
public class GoodsStockPo extends BasePo {

    /**
     * 规格名称
     */
    private String specificationName;
    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 现价
     */
    private BigDecimal currentPrice;

    /**
     * 剩余库存
     */
    private Long surplusStock;

    /**
     * 已使用库存
     */
    private Long usedStock;

    /**
     * 锁定库存
     */
    private Long lockStock;
}
