package com.gemini.business.supermarket.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.supermarket.shopping.po.ShoppingCartPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购物车表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCartPo> {
}
