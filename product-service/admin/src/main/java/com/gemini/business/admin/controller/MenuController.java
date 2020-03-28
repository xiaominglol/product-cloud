package com.gemini.business.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.shiro.utils.UserUtils;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.admin.common.annotation.SysLog;
import com.gemini.business.admin.po.MenuPo;
import com.gemini.business.admin.service.ErrorLogService;
import com.gemini.business.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 菜单控制层
 *
 * @author 小明不读书
 * @date 2017-12-12
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuController {

    @Autowired
    ErrorLogService errorLogService;
    @Autowired
    MenuService menuService;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "platform/menu/menu_list";
    }

    /**
     * 首页菜单权限
     */
    @GetMapping("/list")
    @ResponseBody
    public Message list() {
        try {
            Long userId = UserUtils.getCurrentUser().getId();
            List<MenuPo> list = menuService.getByUserId(userId);
            return Message.success(list);
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, MenuPo po) {
        try {
            QueryWrapper<MenuPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(po.getId())) {
                qw.eq("id", po.getId()).or().eq("pid", po.getId());
            }
            if (!StringUtils.isEmpty(po.getName())) {
                qw.like("name", po.getName());
            }
            // 菜单列表修改的时候要带出子菜单，会用到
            if (!StringUtils.isEmpty(po.getPid())) {
                qw.eq("pid", po.getPid());
            }
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<MenuPo> list = menuService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<MenuPo> list = menuService.list(qw);
                return Message.success(list);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Message detail(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                MenuPo menu = menuService.getById(id);
                return Message.success(menu);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("添加菜单")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody MenuPo po) {
        try {
            if (StringUtils.isEmpty(po.getId())) {
                po.initDict();
                for (MenuPo detailPo : po.getDetailList()) {
                    detailPo.initDict();
                }
                menuService.insertSync(po, po.getDetailList(), true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("更新菜单")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody MenuPo po) {
        try {
            if (!StringUtils.isEmpty(po.getId())) {
                po.initDict();
                for (MenuPo detailPo : po.getDetailList()) {
                    detailPo.initDict();
                }
                menuService.updateSync(po, po.getDetailList(), true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("删除菜单")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                menuService.deleteByIdSync(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }
}
