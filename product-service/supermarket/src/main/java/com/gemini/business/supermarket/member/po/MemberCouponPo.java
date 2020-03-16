package com.gemini.business.supermarket.member.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.entity.Dict;
import com.gemini.boot.framework.mybatis.po.BaseObjectPo;
import com.gemini.business.supermarket.platform.enums.Dicts;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 会员优惠劵表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("member_coupon")
public class MemberCouponPo extends BaseObjectPo {

    /**
     * id
     */
    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 会员昵称
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
     * 创建时间
     */
    private Date createTime;

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

    public void initDicts() {
        Dict stateType = Dicts.get(getStateId());
        setStateCode(stateType.getCode());
        setStateName(stateType.getName());
    }
}
