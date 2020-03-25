package com.gemini.business.admin.enums;

import com.gemini.boot.framework.mybatis.entity.Dict;
import com.gemini.boot.framework.mybatis.service.DictService;
import com.gemini.business.admin.utils.DictUtils;

/**
 * id：402592182904320
 * code：OrgType
 * name：组织架构类型
 * description：组织架构类型
 */
public enum OrgTypeEnum implements DictService {

    /**
     * id：402592182904322
     * code：Group
     * name：集团
     * description：组织架构类型：集团
     */
    Group() {
        @Override
        public Dict dict() {
            return DictUtils.get(402592182904322L);
        }
    },

    /**
     * id：402592182904323
     * code：Company
     * name：公司
     * description：组织架构类型：公司
     */
    Company() {
        @Override
        public Dict dict() {
            return DictUtils.get(402592182904323L);
        }
    },

    /**
     * id：402592182904324
     * code：Department
     * name：部门
     * description：组织架构类型：部门
     */
    Department() {
        @Override
        public Dict dict() {
            return DictUtils.get(402592182904324L);
        }
    }
}
