package com.gemini.business.supermarket.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.supermarket.goods.po.GoodsParamPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品参数表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Mapper
public interface GoodsParamMapper extends BaseMapper<GoodsParamPo> {
}
