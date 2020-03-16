package com.gemini.business.supermarket.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.supermarket.goods.po.GoodsPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Mapper
public interface GoodsMapper extends BaseMapper<GoodsPo> {
}
