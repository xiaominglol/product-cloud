package com.gemini.business.supermarket.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.supermarket.common.annotation.SysLog;
import com.gemini.business.supermarket.goods.po.GoodsBrandPo;
import com.gemini.business.supermarket.goods.service.GoodsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品品牌表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Slf4j
@Controller
@RequestMapping("/goods/brand")
public class GoodsBrandController {

    @Autowired
    GoodsBrandService service;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "goods/brand_list";
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, GoodsBrandPo po) {
        try {
            QueryWrapper<GoodsBrandPo> qw = new QueryWrapper<>();
            qw.orderByAsc("sort");
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<GoodsBrandPo> list = service.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<GoodsBrandPo> list = service.list(qw);
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
                GoodsBrandPo po = service.getById(id);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("添加商品品牌表")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody GoodsBrandPo po) {
        try {
            if (StringUtils.isEmpty(po.getId())) {
                service.insertSync(po, true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("更新商品品牌表")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody GoodsBrandPo po) {
        try {
            if (!StringUtils.isEmpty(po.getId())) {
                po.initDicts();
                service.updateSync(po, true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("删除商品品牌表")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                service.deleteByIdSync(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

}
