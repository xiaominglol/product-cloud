package com.gemini.business.supermarket.goods.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BaseDetailPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("goods")
public class GoodsPo extends BaseDetailPo<GoodsStockPo> {

    /**
     * 名称
     */
    private String name;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 图片
     */
    private String picture;
}
