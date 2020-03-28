package com.gemini.business.admin.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BaseDetailPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户表
 *
 * @author 小明不读书
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class UserPo extends BaseDetailPo<UserRolePo> {

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 密码(MD5加密)
     */
    private String password;

    /**
     * 用户头像
     */
    private String picture;

    /**
     * 组织架构id
     */
    private Long orgId;

    /**
     * 组织架构名称
     */
    private String orgName;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)T
     */
    private Date createTime;
}
