package com.gemini.business.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.admin.common.annotation.SysLog;
import com.gemini.business.admin.po.DictPo;
import com.gemini.business.admin.service.ErrorLogService;
import com.gemini.business.admin.service.PlatformDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典控制层
 *
 * @author 小明不读书
 * @date 2018-10-24
 */
@Slf4j
@Controller
@RequestMapping("/sys/dict")
public class DictController {

    @Autowired
    ErrorLogService errorLogService;
    @Autowired
    PlatformDictService platformDictService;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "platform/dict/dict_list";
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, DictPo po) {
        try {
            QueryWrapper<DictPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(po.getName())) {
                qw.like("name", po.getName()).or().like("code", po.getCode());
            }
            if (!StringUtils.isEmpty(po.getPid())) {
                qw.eq("pid", po.getPid());
                qw.eq("state_code", "Enable");
            }
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<DictPo> list = platformDictService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<DictPo> list = platformDictService.list(qw);
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
                DictPo po = platformDictService.getById(id);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("添加字典")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody DictPo po) {
        try {
            if (StringUtils.isEmpty(po.getId())) {
                platformDictService.insertSync(po, po.getDetailList(), true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("更新字典")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody DictPo po) {
        try {
            if (!StringUtils.isEmpty(po.getId())) {
                platformDictService.updateSync(po, po.getDetailList(), true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("删除字典")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                platformDictService.deleteByIdSync(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }
}
