package com.gemini.business.goods.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BaseDetailPo;
import lombok.Data;

/**
 * 商品评论回复表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Data
@TableName("goods_reply")
public class GoodsReplyPo extends BaseDetailPo<GoodsReplyPo> {

    /**
     * 名称
     */
    private String name;

    /**
     * logo
     */
    private Long brandId;

    /**
     * 排序
     */
    private String brandName;

    /**
     *
     */
    private Long classificationId;

    /**
     *
     */
    private String classificationName;

    /**
     *
     */
    private Long stockId;
}
