package com.gemini.business.goods.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BaseSubPo;
import lombok.Data;

import java.util.List;

/**
 * 商品分类表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Data
@TableName("goods_category")
public class GoodsCategoryPo extends BaseSubPo<GoodsCategoryPo> {


    /**
     * 名称
     */
    private String name;

    /**
     * icon
     */
    private String icon;

    /**
     * 排序
     */
    private Byte sort;

    /**
     * 品牌明细
     */
    @TableField(
            exist = false
    )
    private List<GoodsBrandPo> brandDetailList;

    /**
     * 参数明细
     */
    @TableField(
            exist = false
    )
    private List<GoodsCategoryParamPo> paramDetailList;

    /**
     * 规格明细
     */
    @TableField(
            exist = false
    )
    private List<GoodsSpecificationPo> specificationDetailList;
}
