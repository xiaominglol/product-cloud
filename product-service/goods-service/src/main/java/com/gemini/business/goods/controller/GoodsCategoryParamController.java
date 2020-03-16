package com.gemini.business.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.goods.po.GoodsCategoryParamPo;
import com.gemini.business.goods.service.GoodsCategoryParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import com.gemini.business.common.annotation.SysLog;

/**
 * 商品参数表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Slf4j
@Controller
@RequestMapping("/goods/category/param")
public class GoodsCategoryParamController {

    @Autowired
    GoodsCategoryParamService goodsCategoryParamService;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "goods/category_param_list";
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, GoodsCategoryParamPo po) {
        try {
            QueryWrapper<GoodsCategoryParamPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(po.getCategoryId())) {
                qw.eq("category_id", po.getCategoryId());
            }
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<GoodsCategoryParamPo> list = goodsCategoryParamService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<GoodsCategoryParamPo> list = goodsCategoryParamService.list(qw);
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
                GoodsCategoryParamPo goodsParamPo = goodsCategoryParamService.getById(id);
                return Message.success(goodsParamPo);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

//    @SysLog("添加商品参数表")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody GoodsCategoryParamPo po) {
        try {
            if (StringUtils.isEmpty(po.getId())) {
                goodsCategoryParamService.insertSync(po, po.getDetailList(), true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

//    @SysLog("更新商品参数表")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody GoodsCategoryParamPo po) {
        try {
            if (!StringUtils.isEmpty(po.getId())) {
                goodsCategoryParamService.updateSync(po, po.getDetailList(), true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

//    @SysLog("删除商品参数表")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                goodsCategoryParamService.deleteByIdSync(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

}
