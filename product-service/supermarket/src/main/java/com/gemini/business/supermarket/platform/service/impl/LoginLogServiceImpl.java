package com.gemini.business.supermarket.platform.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseServiceImpl;
import com.gemini.business.supermarket.platform.mapper.LoginLogMapper;
import com.gemini.business.supermarket.platform.po.LoginLogPo;
import com.gemini.business.supermarket.platform.service.LoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 登陆日志表
 *
 * @author 小明不读书
 */
@Service
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLogPo, LoginLogMapper> implements LoginLogService {

    @Override
    public QueryWrapper<LoginLogPo> wrapper(LoginLogPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getUserAccount()), "user_account", po.getUserAccount())
                .eq(!StringUtils.isEmpty(po.getUserName()), "user_name", po.getUserName())
                .eq(!StringUtils.isEmpty(po.getLoginStateId()), "login_state_id", po.getLoginStateId())
                .eq(!StringUtils.isEmpty(po.getLoginStateCode()), "login_state_code", po.getLoginStateCode())
                .eq(!StringUtils.isEmpty(po.getLoginStateName()), "login_state_name", po.getLoginStateName())
                .eq(!StringUtils.isEmpty(po.getMessage()), "message", po.getMessage());
    }
}
