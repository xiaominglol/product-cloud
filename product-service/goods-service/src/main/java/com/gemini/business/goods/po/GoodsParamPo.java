package com.gemini.business.goods.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BasePo;
import lombok.Data;

/**
 * 商品参数表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Data
@TableName("goods_param")
public class GoodsParamPo extends BasePo {

    /**
     * 名称
     */
    private String name;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 值
     */
    private String value;

    /**
     * 排序
     */
    private Byte sort;
}
