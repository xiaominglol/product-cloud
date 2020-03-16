package com.gemini.business.supermarket.goods.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BaseDetailPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商品评论表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("goods_comment")
public class GoodsCommentPo extends BaseDetailPo<GoodsReplyPo> {

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 会员昵称
     */
    private String memberNickname;

    /**
     * 内容
     */
    private String content;

    /**
     * 星级id
     */
    private Long starTypeId;

    /**
     * 星级编码
     */
    private String starTypeCode;

    /**
     * 星级名称
     */
    private String starTypeName;

    /**
     * 创建时间
     */
    private Date createTime;
}
