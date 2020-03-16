package com.gemini.business.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.goods.mapper.GoodsParamMapper;
import com.gemini.business.goods.po.GoodsParamPo;
import com.gemini.business.goods.service.GoodsParamService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 商品参数表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Service
public class GoodsParamServiceImpl extends BaseDetailServiceImpl<GoodsParamPo, GoodsParamPo, GoodsParamMapper, GoodsParamMapper> implements GoodsParamService {

    @Override
    public QueryWrapper<GoodsParamPo> wrapper(GoodsParamPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getName()), "name", po.getName())
                .eq(!StringUtils.isEmpty(po.getGoodsId()), "goods_id", po.getGoodsId())
                .eq(!StringUtils.isEmpty(po.getGoodsName()), "goods_name", po.getGoodsName())
                .eq(!StringUtils.isEmpty(po.getValue()), "value", po.getValue())
                .eq(!StringUtils.isEmpty(po.getSort()), "sort", po.getSort())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getModifyUserId()), "modify_user_id", po.getModifyUserId())
                .eq(!StringUtils.isEmpty(po.getModifyUserName()), "modify_user_name", po.getModifyUserName())
                .eq(!StringUtils.isEmpty(po.getModifyTime()), "modify_time", po.getModifyTime());
    }
}
