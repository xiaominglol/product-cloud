package com.gemini.business.member.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BaseObjectPo;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 会员等级表
 *
 * @author 小明不读书
 * @date Fri Jan 03 15:04:36 CST 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("member_grade")
public class MemberGradePo extends BaseObjectPo {

    /**
     * id
     */
    private Long id;

    /**
     * 等级编码
     */
    private String code;

    /**
     * 等级名称
     */
    private String name;
}
