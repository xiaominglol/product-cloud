package com.gemini.business.supermarket.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.supermarket.common.annotation.SysLog;
import com.gemini.business.supermarket.member.po.MemberAddressPo;
import com.gemini.business.supermarket.member.service.MemberAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员地址表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Slf4j
@RestController
@RequestMapping("/member/address")
public class MemberAddressController {

    @Autowired
    MemberAddressService memberAddressService;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "member/address_list";
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, MemberAddressPo po) {
        try {
            QueryWrapper<MemberAddressPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(po.getMemberId())) {
                qw.eq("member_id", po.getMemberId());
            }
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<MemberAddressPo> list = memberAddressService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<MemberAddressPo> list = memberAddressService.list(qw);
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
                MemberAddressPo memberAddressPo = memberAddressService.getById(id);
                return Message.success(memberAddressPo);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("添加会员地址表")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody MemberAddressPo po) {
        try {
            if (StringUtils.isEmpty(po.getId())) {
                memberAddressService.insertSync(po, false);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("更新会员地址表")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody MemberAddressPo po) {
        try {
            if (!StringUtils.isEmpty(po.getId())) {
                memberAddressService.updateSync(po, false);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("删除会员地址表")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                memberAddressService.deleteByIdSync(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

}
