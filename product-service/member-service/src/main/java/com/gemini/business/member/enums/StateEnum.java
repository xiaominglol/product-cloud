package com.gemini.business.member.enums;

import com.gemini.boot.framework.mybatis.entity.Dict;
import com.gemini.boot.framework.mybatis.service.DictService;

/**
 * id：391933416176128
 * code：State
 * name：状态
 * description：状态
 */
public enum StateEnum implements DictService {

    /**
     * id：391933416176129
     * code：Disable
     * name：禁用
     * description：状态：禁用
     */
    Disable() {
        @Override
        public Dict dict() {
            return Dicts.get(391933416176129L);
        }
    },

    /**
     * id：391933416176131
     * code：Enable
     * name：启用
     * description：状态：启用
     */
    Enable() {
        @Override
        public Dict dict() {
            return Dicts.get(391933416176131L);
        }
    }
}
