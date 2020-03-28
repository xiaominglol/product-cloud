package com.gemini.business.goods.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BasePo;
import lombok.Data;

/**
 * 商品分类规格表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Data
@TableName("goods_specification")
public class GoodsSpecificationPo extends BasePo {

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
     * 值
     */
    private String value;

    /**
     * 排序
     */
    private Byte sort;
}
