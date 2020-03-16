package com.gemini.business.supermarket.order.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BaseDetailPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单退款表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("order_refund")
public class OrderRefundPo extends BaseDetailPo<OrderRefundPo> {

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单商品id
     */
    private Long orderGoodsId;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 会员名称
     */
    private String memberNickname;

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
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 退款原因
     */
    private String refundReason;

    /**
     * 状态id
     */
    private Long stateId;

    /**
     * 状态编码s
     */
    private String stateCode;

    /**
     * 状态名称
     */
    private String stateName;

    /**
     * 退款时间
     */
    private Date refundTime;
}
