package com.gemini.business.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.admin.po.ErrorLogPo;
import com.gemini.business.admin.po.LoginLogPo;
import com.gemini.business.admin.po.OptLogPo;
import com.gemini.business.admin.service.ErrorLogService;
import com.gemini.business.admin.service.LoginLogService;
import com.gemini.business.admin.service.OptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 组织架构-控制层
 *
 * @author 小明不读书
 * @date 2018-06-09
 */
@RestController
@RequestMapping("/admin/log")
public class LogController {

    @Autowired
    ErrorLogService errorLogService;
    @Autowired
    LoginLogService loginLogService;
    @Autowired
    OptLogService optLogService;

    @GetMapping("/loginLog")
    @ResponseBody
    public Message loginLogList(LayUiPage layUiPage, LoginLogPo loginLog) {

        QueryWrapper<LoginLogPo> qw = new QueryWrapper<>();
        if (!StringUtils.isEmpty(loginLog.getUserName())) {
            qw.like("user_name", loginLog.getUserName());
        }
        if (!StringUtils.isEmpty(loginLog.getLoginStateCode())) {
            qw.eq("state_code", loginLog.getLoginStateCode());
        }
        qw.orderByDesc("create_time");
        if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
            IPage<LoginLogPo> list = loginLogService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
            return Message.success(list);
        } else {
            List<LoginLogPo> list = loginLogService.list(qw);
            return Message.success(list);
        }

    }

    @GetMapping("/optLog")
    @ResponseBody
    public Message optLogList(LayUiPage layUiPage, OptLogPo optLogPo) {

        QueryWrapper<OptLogPo> qw = new QueryWrapper<>();
        if (!StringUtils.isEmpty(optLogPo.getDescription())) {
            qw.like("description", optLogPo.getDescription());
        }
        qw.orderByDesc("create_time");
        if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
            IPage<OptLogPo> list = optLogService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
            return Message.success(list);
        } else {
            List<OptLogPo> list = optLogService.list(qw);
            return Message.success(list);
        }

    }

    @GetMapping("/errorLog")
    @ResponseBody
    public Message errorLogList(LayUiPage layUiPage, ErrorLogPo errorLogPo) {

        QueryWrapper<ErrorLogPo> qw = new QueryWrapper<>();
        if (!StringUtils.isEmpty(errorLogPo.getUserName())) {
            qw.like("user_name", errorLogPo.getUserName());
        }
        qw.orderByDesc("create_time");
        if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
            IPage<ErrorLogPo> list = errorLogService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
            return Message.success(list);
        } else {
            List<ErrorLogPo> list = errorLogService.list(qw);
            return Message.success(list);
        }

    }

}
