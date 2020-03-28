package com.gemini.business.goods.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BasePo;
import lombok.Data;

/**
 * 商品品牌表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Data
@TableName("goods_brand")
public class GoodsBrandPo extends BasePo {

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
     * logo
     */
    private String logo;

    /**
     * 排序
     */
    private Byte sort;
}
