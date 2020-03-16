package com.gemini.business.supermarket.shopping.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.supermarket.common.annotation.SysLog;
import com.gemini.business.supermarket.shopping.po.ShoppingCartPo;
import com.gemini.business.supermarket.shopping.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 购物车表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Slf4j
@Controller
@RequestMapping("/shopping/cart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "shopping/cart_list";
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, ShoppingCartPo po) {
        try {
            QueryWrapper<ShoppingCartPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(po.getMemberId())) {
                qw.eq("member_id", po.getMemberId());
            }
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<ShoppingCartPo> list = shoppingCartService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<ShoppingCartPo> list = shoppingCartService.list(qw);
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
                ShoppingCartPo shoppingCartPo = shoppingCartService.getById(id);
                return Message.success(shoppingCartPo);
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
    public Message add(@RequestBody ShoppingCartPo po) {
        try {
            if (StringUtils.isEmpty(po.getId())) {
                po.setCreateTime(new Date());
                shoppingCartService.insertSync(po, false);
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
    public Message update(@RequestBody ShoppingCartPo po) {
        try {
            if (!StringUtils.isEmpty(po.getId())) {
                shoppingCartService.updateSync(po, false);
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
                shoppingCartService.deleteByIdSync(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

}
