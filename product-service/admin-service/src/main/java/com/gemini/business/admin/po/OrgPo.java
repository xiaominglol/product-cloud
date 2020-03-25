package com.gemini.business.admin.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.entity.Dict;
import com.gemini.boot.framework.mybatis.po.BaseSubPo;
import com.gemini.business.admin.utils.DictUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组织架构表
 *
 * @author 小明不读书
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("org")
public class OrgPo extends BaseSubPo<OrgPo> {

    /**
     * 组织架构名称
     */
    private String name;

    /**
     * 组织架构类型id
     */
    private Long orgTypeId;

    /**
     * 组织架构类型编码
     */
    private String orgTypeCode;

    /**
     * 组织架构类型名称
     */
    private String orgTypeName;

    /**
     * 排序
     */
    private String sort;

    public void initDict() {
        Dict orgType = DictUtils.get(getOrgTypeId());
        setOrgTypeCode(orgType.getCode());
        setOrgTypeName(orgType.getName());
    }
}
