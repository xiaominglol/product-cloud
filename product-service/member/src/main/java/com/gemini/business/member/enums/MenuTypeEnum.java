package com.gemini.business.member.enums;

import com.gemini.boot.framework.mybatis.entity.Dict;
import com.gemini.boot.framework.mybatis.service.DictService;

/**
 * id：402555407246848
 * code：MenuType
 * name：菜单类型
 * description：菜单类型
 */
public enum MenuTypeEnum implements DictService {

    /**
     * id：402555407246850
     * code：Directory
     * name：目录
     * description：菜单类型：目录
     */
    Directory() {
        @Override
        public Dict dict() {
            return Dicts.get(402555407246850L);
        }
    },

    /**
     * id：402555407246851
     * code：Menu
     * name：菜单
     * description：菜单类型：菜单
     */
    Menu() {
        @Override
        public Dict dict() {
            return Dicts.get(402555407246851L);
        }
    },

    /**
     * id：402555407246852
     * code：Button
     * name：按钮
     * description：菜单类型：按钮
     */
    Button() {
        @Override
        public Dict dict() {
            return Dicts.get(402555407246852L);
        }
    }
}
