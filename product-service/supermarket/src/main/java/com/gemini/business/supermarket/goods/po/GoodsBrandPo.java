package com.gemini.business.supermarket.goods.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.entity.Dict;
import com.gemini.boot.framework.mybatis.po.BasePo;
import com.gemini.business.supermarket.platform.enums.Dicts;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品品牌表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("goods_brand")
public class GoodsBrandPo extends BasePo {

    /**
     * 名称
     */
    private String name;

    /**
     * logo
     */
    private String logo;

    /**
     * 排序
     */
    private Byte sort;

    public void initDicts() {
        Dict stateType = Dicts.get(getStateId());
        setStateCode(stateType.getCode());
        setStateName(stateType.getName());
    }
}
