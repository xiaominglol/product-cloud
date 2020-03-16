package com.gemini.business.supermarket.homepage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.supermarket.common.annotation.SysLog;
import com.gemini.business.supermarket.homepage.po.HomepageActivityPo;
import com.gemini.business.supermarket.homepage.service.HomepageActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Slf4j
@RestController
@RequestMapping("/homepage/activity")
public class HomepageActivityController {

    @Autowired
    HomepageActivityService homepageActivityService;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "homepage/_list";
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, HomepageActivityPo po) {
        try {
            QueryWrapper<HomepageActivityPo> qw = new QueryWrapper<>();
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<HomepageActivityPo> list = homepageActivityService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<HomepageActivityPo> list = homepageActivityService.list(qw);
                return Message.success(list);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Message detail(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                HomepageActivityPo homepageActivityPo = homepageActivityService.getById(id);
                return Message.success(homepageActivityPo);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("添加购物车表")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody HomepageActivityPo po) {
        try {
            if (StringUtils.isEmpty(po.getId())) {
                homepageActivityService.insertSync(po, po.getDetailList(), false);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("更新购物车表")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody HomepageActivityPo po) {
        try {
            if (!StringUtils.isEmpty(po.getId())) {
                homepageActivityService.updateSync(po, po.getDetailList(), false);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("删除购物车表")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                homepageActivityService.deleteByIdSync(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

}
