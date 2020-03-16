package com.gemini.business.supermarket.goods.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品品牌表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("goods_category_brand")
public class GoodsCategoryBrandPo extends BasePo {

    /**
     * 商品id
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * logo
     */
    private String logo;

    /**
     * 排序
     */
    private Byte sort;
}
