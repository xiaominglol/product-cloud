package com.gemini.business.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseServiceImpl;
import com.gemini.business.admin.mapper.OptLogMapper;
import com.gemini.business.admin.po.OptLogPo;
import com.gemini.business.admin.service.OptLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 操作日志表
 *
 * @author 小明不读书
 */
@Service
public class OptLogServiceImpl extends BaseServiceImpl<OptLogPo, OptLogMapper> implements OptLogService {

    @Override
    public QueryWrapper<OptLogPo> wrapper(OptLogPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getUserAccount()), "user_account", po.getUserAccount())
                .eq(!StringUtils.isEmpty(po.getUserName()), "user_name", po.getUserName())
                .eq(!StringUtils.isEmpty(po.getOptTypeId()), "opt_type_id", po.getOptTypeId())
                .eq(!StringUtils.isEmpty(po.getOptTypeCode()), "opt_type_code", po.getOptTypeCode())
                .eq(!StringUtils.isEmpty(po.getOptTypeName()), "opt_type_name", po.getOptTypeName())
                .eq(!StringUtils.isEmpty(po.getDescription()), "description", po.getDescription())
                .eq(!StringUtils.isEmpty(po.getUrl()), "url", po.getUrl())
                .eq(!StringUtils.isEmpty(po.getMethod()), "method", po.getMethod())
                .eq(!StringUtils.isEmpty(po.getParams()), "params", po.getParams())
                .eq(!StringUtils.isEmpty(po.getResult()), "result", po.getResult())
                .eq(!StringUtils.isEmpty(po.getIp()), "ip", po.getIp())
                .eq(!StringUtils.isEmpty(po.getTime()), "time", po.getTime());
    }
}
