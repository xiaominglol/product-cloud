package com.gemini.business.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.admin.common.annotation.SysLog;
import com.gemini.business.admin.po.RolePo;
import com.gemini.business.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 小明不读书
 * @date 2017-11-03
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    RoleService service;


    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, RolePo po) {

        QueryWrapper<RolePo> qw = new QueryWrapper<>();
        //如果是分页查询
        if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
            if (!StringUtils.isEmpty(po.getName())) {
                qw.like("name", po.getName()).or().like("code", po.getName());
            }
            IPage<RolePo> list = service.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
            return Message.success(list);
        } else {//否则查询全部有效
            qw.eq("state_code", "Enable");
            List<RolePo> list = service.list(qw);
            return Message.success(list);
        }

    }

    @GetMapping("/{id}")
    @ResponseBody
    public Message detail(@PathVariable("id") Long id) {

        if (!StringUtils.isEmpty(id)) {
            RolePo po = service.getById(id);
            return Message.success(po);
        } else {
            return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
        }

    }

    @SysLog("添加角色")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody RolePo po) {

        if (StringUtils.isEmpty(po.getId())) {
            service.insertSync(po, po.getDetailList(), false);
            return Message.success(po);
        } else {
            return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
        }

    }

    @SysLog("更新角色")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody RolePo po) {

        if (!StringUtils.isEmpty(po.getId())) {
            service.updateSync(po, po.getDetailList(), false);
            return Message.success(po);
        } else {
            return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
        }

    }

    @SysLog("删除角色")
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

    /**
     * 获得权限
     *
     * @param id 角色主键id
     * @return
     */
    @GetMapping("/aut/{id}")
    @ResponseBody
    public Message getAut(@PathVariable("id") Long id) {

        if (!StringUtils.isEmpty(id)) {
            List<Map<String, String>> list = service.getAut(id);
            List<String> idList = new ArrayList<>();
            for (Map<String, String> map : list) {
                idList.add(map.get("menuId"));
            }
            return Message.success(idList);
        } else {
            return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
        }

    }
}
