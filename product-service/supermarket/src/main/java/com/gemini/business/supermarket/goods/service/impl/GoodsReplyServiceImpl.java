package com.gemini.business.supermarket.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseServiceImpl;
import com.gemini.business.supermarket.goods.mapper.GoodsReplyMapper;
import com.gemini.business.supermarket.goods.po.GoodsReplyPo;
import com.gemini.business.supermarket.goods.service.GoodsReplyService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 商品评论回复表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Service
public class GoodsReplyServiceImpl extends BaseServiceImpl<GoodsReplyPo, GoodsReplyMapper> implements GoodsReplyService {

    @Override
    public QueryWrapper<GoodsReplyPo> wrapper(GoodsReplyPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getCommentId()), "comment_id", po.getCommentId())
                .eq(!StringUtils.isEmpty(po.getMemberId()), "member_id", po.getMemberId())
                .eq(!StringUtils.isEmpty(po.getMemberNickname()), "member_nickname", po.getMemberNickname())
                .eq(!StringUtils.isEmpty(po.getContent()), "content", po.getContent())
                .eq(!StringUtils.isEmpty(po.getCreateTime()), "create_time", po.getCreateTime())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getModifyUserId()), "modify_user_id", po.getModifyUserId())
                .eq(!StringUtils.isEmpty(po.getModifyUserName()), "modify_user_name", po.getModifyUserName())
                .eq(!StringUtils.isEmpty(po.getModifyTime()), "modify_time", po.getModifyTime());
    }
}
