package com.gemini.business.member.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BaseObjectPo;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 会员地址表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("member_address")
public class MemberAddressPo extends BaseObjectPo {

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
     * 收货人名称
     */
    private String name;

    /**
     * 收货人电话号码
     */
    private String phone;

    /**
     * 省/直辖市
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区/县
     */
    private String county;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 是否默认
     */
    private Byte isDefault;
}
