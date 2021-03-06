package com.gemini.business.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.goods.po.GoodsCommentPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评论表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Mapper
public interface GoodsCommentMapper extends BaseMapper<GoodsCommentPo> {
}
