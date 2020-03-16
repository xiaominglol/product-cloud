package com.gemini.business.supermarket.goods.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.supermarket.common.annotation.SysLog;
import com.gemini.business.supermarket.goods.po.GoodsCategoryPo;
import com.gemini.business.supermarket.goods.service.GoodsCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Slf4j
@Controller
@RequestMapping("/goods/category")
public class GoodsCategoryController {

    @Autowired
    GoodsCategoryService service;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "goods/category_list";
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, GoodsCategoryPo po) {
        try {
            QueryWrapper<GoodsCategoryPo> qw = new QueryWrapper<>();
            // 菜单列表修改的时候要带出子菜单，会用到
            if (!StringUtils.isEmpty(po.getPid())) {
                qw.eq("pid", po.getPid());
            }
            if (!StringUtils.isEmpty(po.getId())) {
                qw.eq("id", po.getId()).or().eq("pid", po.getId());
            }
            qw.orderByAsc("pid", "sort");
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<GoodsCategoryPo> list = service.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<GoodsCategoryPo> list = service.list(qw);
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
                GoodsCategoryPo po = service.getById(id);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("添加商品分类表")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody GoodsCategoryPo po) {
        try {
            if (StringUtils.isEmpty(po.getId())) {
                service.insertSync(po, po.getDetailList(), true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("批量添加商品分类表")
    @PostMapping("/batchAdd")
    @ResponseBody
    public Message batchAdd(@RequestBody String batchData) {
        try {
//            if (StringUtils.isEmpty(batchData)) {
            List<GoodsCategoryPo> list = JSONArray.parseArray(batchData, GoodsCategoryPo.class);
            for (GoodsCategoryPo po : list) {
                service.insertSync(po, po.getDetailList(), true);
            }
            return Message.success(list);
//                return null;
//            } else {
//                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("更新商品分类表")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody GoodsCategoryPo po) {
        try {
            if (!StringUtils.isEmpty(po.getId())) {
                service.updateSync(po, po.getDetailList(), true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("删除商品分类表")
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
