package com.gemini.business.member.enums;

import com.gemini.boot.framework.mybatis.entity.Dict;
import com.gemini.boot.framework.mybatis.service.DictService;

/**
 * id：402609161446912
 * code：Target
 * name：打开方式
 * description：打开方式
 */
public enum TargetEnum implements DictService {

    /**
     * id：402609161446914
     * code：_blank
     * name：新窗口
     * description：打开方式：新窗口
     */
    _blank() {
        @Override
        public Dict dict() {
            return Dicts.get(402609161446914L);
        }
    },

    /**
     * id：402609161446915
     * code：_self
     * name：当前窗口
     * description：打开方式：当前窗口
     */
    _self() {
        @Override
        public Dict dict() {
            return Dicts.get(402609161446915L);
        }
    }
}
