package com.gemini.business.supermarket.platform.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BaseObjectPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 操作日志表
 *
 * @author 小明不读书
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("platform_opt_log")
public class OptLogPo extends BaseObjectPo {

    /**
     * 主鍵ID
     */
    private Long id;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 操作类型id
     */
    private Long optTypeId;

    /**
     * 操作类型编码
     */
    private String optTypeCode;

    /**
     * 操作类型名称
     */
    private String optTypeName;

    /**
     * 操作描述
     */
    private String description;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求方法名称
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 返回结果
     */
    private String result;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 耗时（ms）
     */
    private Long time;

    /**
     * 操作时间
     */
    private Date createTime;
}
