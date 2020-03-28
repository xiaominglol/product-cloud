package com.gemini.business.member.enums;

import com.gemini.boot.framework.mybatis.entity.Dict;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典存儲對象
 */
public class Dicts {

    /**
     * 私有化構造方法
     */
    private Dicts() {
        super();
    }

    /**
     * 儲存字典的MAP
     */
    private static final Map<Long, Dict> dicts = new HashMap<>();

    static {
        dicts.put(0L, new Dict());
        dicts.put(391933416176129L, new Dict().setId(391933416176129L).setCode("Disable").setName("禁用"));
        dicts.put(391933416176131L, new Dict().setId(391933416176131L).setCode("Enable").setName("启用"));

        dicts.put(402555407246850L, new Dict().setId(402555407246850L).setCode("Directory").setName("目录"));
        dicts.put(402555407246851L, new Dict().setId(402555407246851L).setCode("Menu").setName("菜单"));
        dicts.put(402555407246852L, new Dict().setId(402555407246852L).setCode("Button").setName("按钮"));

        dicts.put(402592182904322L, new Dict().setId(402592182904322L).setCode("Group").setName("集团"));
        dicts.put(402592182904323L, new Dict().setId(402592182904323L).setCode("Company").setName("公司"));
        dicts.put(402592182904324L, new Dict().setId(402592182904324L).setCode("Department").setName("部门"));

        dicts.put(402609161446914L, new Dict().setId(402609161446914L).setCode("_blank").setName("新窗口"));
        dicts.put(402609161446915L, new Dict().setId(402609161446915L).setCode("_self").setName("当前窗口"));

    }

    /**
     * 根據ID獲取字典對象
     *
     * @param id 字典對象ID
     * @return Dict
     */
    public static Dict get(Long id) {
        Dict dict = dicts.get(id);
        if (dict == null) {
            dict = new Dict();
            dict.setId(id);
        }
        return dict;
    }
}
