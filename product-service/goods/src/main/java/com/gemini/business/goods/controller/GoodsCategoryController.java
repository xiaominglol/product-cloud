package com.gemini.business.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.goods.po.GoodsCategoryPo;
import com.gemini.business.goods.service.GoodsCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import com.gemini.business.common.annotation.SysLog;

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
    GoodsCategoryService goodsCategoryService;

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
                qw.eq("id", po.getId()).or().eq("pid",po.getId());
            }
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<GoodsCategoryPo> list = goodsCategoryService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<GoodsCategoryPo> list = goodsCategoryService.list(qw);
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
                GoodsCategoryPo goodsCategoryPo = goodsCategoryService.getById(id);
                return Message.success(goodsCategoryPo);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

//    @SysLog("添加商品分类表")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody GoodsCategoryPo po) {
        try {
            if (StringUtils.isEmpty(po.getId())) {
                goodsCategoryService.insertSync(po, po.getDetailList(), true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

//    @SysLog("更新商品分类表")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody GoodsCategoryPo po) {
        try {
            if (!StringUtils.isEmpty(po.getId())) {
                goodsCategoryService.updateSync(po, po.getDetailList(), true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

//    @SysLog("删除商品分类表")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                goodsCategoryService.deleteByIdSync(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

}
