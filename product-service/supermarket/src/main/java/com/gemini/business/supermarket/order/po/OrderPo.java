package com.gemini.business.supermarket.order.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BaseObjectPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 * 遇到关键字除了要加``，还要指定value =
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "`order`")
public class OrderPo extends BaseObjectPo {

    /**
     * id
     */
    private Long id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 会员名称
     */
    private String memberNickname;

    /**
     * 优惠劵id
     */
    private Long couponId;

    /**
     * 优惠劵名称
     */
    private String couponName;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 支付金额
     */
    private BigDecimal payAmount;

    /**
     * 收货人名称
     */
    private String receiverName;

    /**
     * 收货人电话号码
     */
    private String receiverPhone;

    /**
     * 收货人省/直辖市
     */
    private String receiverProvince;

    /**
     * 收货人市
     */
    private String receiverCity;

    /**
     * 收货人区/县
     */
    private String receiverCounty;

    /**
     * 收货人详细地址
     */
    private String receiverDetailAddress;

    /**
     * 状态id
     */
    private Long stateId;

    /**
     * 状态编码
     */
    private String stateCode;

    /**
     * 状态名称
     */
    private String stateName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 收货时间
     */
    private Date receiveTime;
}
