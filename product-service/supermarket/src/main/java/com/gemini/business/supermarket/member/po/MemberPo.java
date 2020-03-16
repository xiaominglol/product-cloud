package com.gemini.business.supermarket.member.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.entity.Dict;
import com.gemini.boot.framework.mybatis.po.BaseObjectPo;
import com.gemini.business.supermarket.platform.enums.Dicts;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 会员表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("member")
public class MemberPo extends BaseObjectPo {

    /**
     *
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 号码
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号码
     */
    private Integer idCardNo;

    /**
     * 等级编码
     */
    private String gradeCode;

    /**
     * 等级名称
     */
    private String gradeName;

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
