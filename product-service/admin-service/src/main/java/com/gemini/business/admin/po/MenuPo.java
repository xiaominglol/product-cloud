package com.gemini.business.admin.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.entity.Dict;
import com.gemini.boot.framework.mybatis.po.BaseSubPo;
import com.gemini.business.admin.utils.DictUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单表
 *
 * @author 小明不读书
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("menu")
public class MenuPo extends BaseSubPo<MenuPo> {

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 菜单icon
     */
    private String icon;

    /**
     * 菜单类型id
     */
    private Long menuTypeId;

    /**
     * 菜单类型编码
     */
    private String menuTypeCode;

    /**
     * 菜单类型名称
     */
    private String menuTypeName;

    /**
     * 打开方式id
     */
    private Long targetId;

    /**
     * 打开方式编码
     */
    private String targetCode;

    /**
     * 打开方式名称
     */
    private String targetName;

    /**
     * 权限id
     */
    private Long permissionId;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 排序
     */
    private String sort;

    public void initDict() {
        Dict menuType = DictUtils.get(getMenuTypeId());
        setMenuTypeCode(menuType.getCode());
        setMenuTypeName(menuType.getName());
        Dict target = DictUtils.get(getTargetId());
        setTargetCode(target.getCode());
        setTargetName(target.getName());
        Dict permission = DictUtils.get(getPermissionId());
        setPermissionCode(permission.getCode());
        setPermissionName(permission.getName());
    }
}
