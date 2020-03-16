package com.gemini.business.supermarket.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.supermarket.common.annotation.SysLog;
import com.gemini.business.supermarket.member.po.MemberGradePo;
import com.gemini.business.supermarket.member.service.MemberGradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员等级表
 *
 * @author 小明不读书
 * @date Fri Jan 03 15:04:36 CST 2020
 */
@Slf4j
@Controller
@RequestMapping("/member/grade")
public class MemberGradeController {

    @Autowired
    MemberGradeService memberGradeService;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "member/grade_list";
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, MemberGradePo po) {
        try {
            QueryWrapper<MemberGradePo> qw = new QueryWrapper<>();
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<MemberGradePo> list = memberGradeService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<MemberGradePo> list = memberGradeService.list(qw);
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
                MemberGradePo memberGradePo = memberGradeService.getById(id);
                return Message.success(memberGradePo);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("添加会员等级表")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody MemberGradePo po) {
        try {
            if (StringUtils.isEmpty(po.getId())) {
                memberGradeService.insertSync(po, false);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("更新会员等级表")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody MemberGradePo po) {
        try {
            if (!StringUtils.isEmpty(po.getId())) {
                memberGradeService.updateSync(po, false);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("删除会员等级表")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                memberGradeService.deleteByIdSync(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

}
