package com.gemini.business.supermarket.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.mybatis.enums.StateEnum;
import com.gemini.boot.framework.mybatis.utils.BeanUtils;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.supermarket.common.annotation.SysLog;
import com.gemini.business.supermarket.member.po.MemberCouponPo;
import com.gemini.business.supermarket.member.service.MemberCouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 会员优惠劵表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Slf4j
@RestController
@RequestMapping("/member/coupon")
public class MemberCouponController {

    @Autowired
    MemberCouponService memberCouponService;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "member/coupon_list";
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, MemberCouponPo po) {
        try {
            QueryWrapper<MemberCouponPo> qw = new QueryWrapper<>();
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<MemberCouponPo> list = memberCouponService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<MemberCouponPo> list = memberCouponService.list(qw);
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
                MemberCouponPo memberCouponPo = memberCouponService.getById(id);
                return Message.success(memberCouponPo);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("添加会员优惠劵表")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody MemberCouponPo po) {
        try {
            if (StringUtils.isEmpty(po.getId())) {
                po.setCreateTime(new Date());
                BeanUtils.setDict(StateEnum.Enable, po);
                memberCouponService.insertSync(po, false);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("更新会员优惠劵表")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody MemberCouponPo po) {
        try {
            if (!StringUtils.isEmpty(po.getId())) {
                po.initDicts();
                memberCouponService.updateSync(po, false);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("删除会员优惠劵表")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                memberCouponService.deleteByIdSync(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

}
