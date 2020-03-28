package com.gemini.business.supermarket.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.supermarket.common.annotation.SysLog;
import com.gemini.business.supermarket.platform.po.OrgPo;
import com.gemini.business.supermarket.platform.service.ErrorLogService;
import com.gemini.business.supermarket.platform.service.OrgService;
import com.gemini.business.supermarket.platform.utils.TreeSelectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 组织架构-控制层
 *
 * @author 小明不读书
 * @date 2018-06-09
 */
@Controller
@RequestMapping("/admin/org")
public class OrgController {

    @Autowired
    ErrorLogService errorLogService;
    @Autowired
    OrgService orgService;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "platform/org/org_list";
    }

    /**
     * 构建下拉树
     *
     * @return
     */
    @GetMapping("/treeSelect")
    @ResponseBody
    public Message treeSelect() {
        try {
            QueryWrapper<OrgPo> qw = new QueryWrapper<>();
            List<OrgPo> orgList = orgService.list(qw);
            List<Map<String, Object>> list = TreeSelectUtil.getTreeSelect(orgList);
            return Message.success(list);
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, OrgPo po) {
        try {
            QueryWrapper<OrgPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(po.getName())) {
                qw.like("name", po.getName());
            }
            if (!StringUtils.isEmpty(po.getOrgTypeCode())) {
                qw.like("org_type_code", po.getOrgTypeCode());
            }
            if (!StringUtils.isEmpty(po.getPid())) {
                qw.eq("pid", po.getPid());
            }
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<OrgPo> list = orgService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<OrgPo> list = orgService.list(qw);
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
                OrgPo po = orgService.getById(id);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("添加组织架构")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody OrgPo po) {
        try {
            if (StringUtils.isEmpty(po.getId())) {
                po.initDict();
                for (OrgPo detailPo : po.getDetailList()) {
                    detailPo.initDict();
                }
                orgService.insertSync(po, po.getDetailList(), true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("更新组织架构")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody OrgPo po) {
        try {
            if (!StringUtils.isEmpty(po.getId())) {
                po.initDict();
                for (OrgPo detailPo : po.getDetailList()) {
                    detailPo.initDict();
                }
                orgService.updateSync(po, po.getDetailList(), true);
                return Message.success(po);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("删除组织架构")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                orgService.deleteByIdSync(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            return Message.fail(e.getMessage());
        }
    }

}
