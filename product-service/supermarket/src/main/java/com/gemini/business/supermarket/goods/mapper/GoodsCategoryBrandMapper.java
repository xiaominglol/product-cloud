package com.gemini.business.supermarket.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.supermarket.goods.po.GoodsCategoryBrandPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类品牌表
 *
 * @author 小明不读书
 * @date Tue Jan 21 14:55:00 CST 2020
 */
@Mapper
public interface GoodsCategoryBrandMapper extends BaseMapper<GoodsCategoryBrandPo> {
}
