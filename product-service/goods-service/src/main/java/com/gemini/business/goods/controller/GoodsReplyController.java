package com.gemini.business.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.goods.po.GoodsReplyPo;
import com.gemini.business.goods.service.GoodsReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import com.gemini.business.common.annotation.SysLog;

/**
 * 商品评论回复表
 *
 * @author 小明不读书
 * @date Tue Nov 26 21:22:00 CST 2019
 */
@Slf4j
@Controller
@RequestMapping("/goods/reply")
public class GoodsReplyController {

    @Autowired
    GoodsReplyService goodsReplyService;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "reply_list";
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, GoodsReplyPo goodsReplyPo) {
        try {
            QueryWrapper<GoodsReplyPo> qw = new QueryWrapper<>();
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<GoodsReplyPo> list = goodsReplyService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<GoodsReplyPo> list = goodsReplyService.list(qw);
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
                GoodsReplyPo goodsReplyPo = goodsReplyService.getById(id);
                return Message.success(goodsReplyPo);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

//    @SysLog("添加商品评论回复表")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody GoodsReplyPo po) {
        try {
            if (StringUtils.isEmpty(po.getId())) {
                goodsReplyService.insertSync(po, true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

//    @SysLog("更新商品评论回复表")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody GoodsReplyPo po) {
        try {
            if (!StringUtils.isEmpty(po.getId())) {
                goodsReplyService.updateSync(po, true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

//    @SysLog("删除商品评论回复表")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                goodsReplyService.deleteByIdSync(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

}
