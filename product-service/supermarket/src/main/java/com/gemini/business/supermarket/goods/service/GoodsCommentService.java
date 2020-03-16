package com.gemini.business.supermarket.goods.service;

import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.supermarket.goods.mapper.GoodsCommentMapper;
import com.gemini.business.supermarket.goods.mapper.GoodsReplyMapper;
import com.gemini.business.supermarket.goods.po.GoodsCommentPo;
import com.gemini.business.supermarket.goods.po.GoodsReplyPo;

/**
 * 商品评论表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
public interface GoodsCommentService extends BaseDetailService<GoodsCommentPo, GoodsReplyPo, GoodsCommentMapper, GoodsReplyMapper> {
}
