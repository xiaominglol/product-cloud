package com.gemini.business.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.admin.common.annotation.SysLog;
import com.gemini.business.admin.po.DictPo;
import com.gemini.business.admin.service.PlatformDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 字典控制层
 *
 * @author 小明不读书
 * @date 2018-10-24
 */
@Slf4j
@RestController
@RequestMapping("/admin/dict")
public class DictController {

    @Autowired
    PlatformDictService service;

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, DictPo po) {

        QueryWrapper<DictPo> qw = new QueryWrapper<>();
        if (!StringUtils.isEmpty(po.getName())) {
            qw.like("name", po.getName()).or().like("code", po.getCode());
        }
        if (!StringUtils.isEmpty(po.getPid())) {
            qw.eq("pid", po.getPid());
            qw.eq("state_code", "Enable");
        }
        if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
            IPage<DictPo> list = service.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
            return Message.success(list);
        } else {
            List<DictPo> list = service.list(qw);
            return Message.success(list);
        }

    }

    @GetMapping("/{id}")
    @ResponseBody
    public Message detail(@PathVariable("id") Long id) {

        if (!StringUtils.isEmpty(id)) {
            DictPo po = service.getById(id);
            return Message.success(po);
        } else {
            return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
        }

    }

    @SysLog("添加字典")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody DictPo po) {

        if (StringUtils.isEmpty(po.getId())) {
            service.insertSync(po, po.getDetailList(), true);
            return Message.success(po);
        } else {
            return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
        }

    }

    @SysLog("更新字典")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody DictPo po) {

        if (!StringUtils.isEmpty(po.getId())) {
            service.updateSync(po, po.getDetailList(), true);
            return Message.success(po);
        } else {
            return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
        }

    }

    @SysLog("删除字典")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {

        if (!StringUtils.isEmpty(id)) {
            service.deleteByIdSync(id);
            return Message.success(null);
        } else {
            return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
        }

    }

    @PostMapping("/upload")
    public Message addSubject(MultipartFile file) {
        //上传过来excel文件
        service.upload(file, service);
        return Message.success(null);
    }
}
