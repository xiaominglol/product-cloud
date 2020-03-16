package com.gemini.business.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.mybatis.enums.StateEnum;
import com.gemini.boot.framework.mybatis.utils.BeanUtils;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.member.entity.LoginParam;
import com.gemini.business.member.po.MemberPo;
import com.gemini.business.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 会员表
 *
 * @author 小明不读书
 * @date Wed Dec 04 09:34:37 CST 2019
 */
@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "member/member_list";
    }

    //    @SysLog("会员登陆")
    @PostMapping("/login")
    @ResponseBody
    public Message login(@RequestBody LoginParam loginParam) {
        try {
            if (!StringUtils.isEmpty(loginParam.getPhone()) && !StringUtils.isEmpty(loginParam.getCode())) {
                MemberPo po = new MemberPo();
                po.setPhone(loginParam.getPhone());
                MemberPo byParam = memberService.getByParam(po);
                if (StringUtils.isEmpty(byParam)) {
                    //注册
                    po.setCreateTime(new Date());
                    BeanUtils.setDict(StateEnum.Enable, po);
                    memberService.insertSync(po, false);
                    po = new MemberPo();
                    po.setPhone(loginParam.getPhone());
                    byParam = memberService.getByParam(po);
                } else {
                    //登陆
                }
                return Message.success(byParam);
            } else {
                return Message.fail("手机号或者验证码不能为空");
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, MemberPo po) {
        try {
            QueryWrapper<MemberPo> qw = new QueryWrapper<>();
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<MemberPo> list = memberService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<MemberPo> list = memberService.list(qw);
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
                MemberPo memberPo = memberService.getById(id);
                return Message.success(memberPo);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    //    @SysLog("添加会员表")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody MemberPo po) {
        try {
            if (StringUtils.isEmpty(po.getId())) {
                po.setCreateTime(new Date());
                BeanUtils.setDict(StateEnum.Enable, po);
                memberService.insertSync(po, false);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    //    @SysLog("更新会员表")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody MemberPo po) {
        try {
            if (!StringUtils.isEmpty(po.getId())) {
                po.initDicts();
                memberService.updateSync(po, false);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    //    @SysLog("删除会员表")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                memberService.deleteByIdSync(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

}
