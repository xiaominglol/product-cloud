package com.gemini.business.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.admin.common.annotation.SysLog;
import com.gemini.business.admin.po.OrgPo;
import com.gemini.business.admin.service.OrgService;
import com.gemini.business.admin.utils.TreeSelectUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/admin/org")
public class OrgController {

    @Autowired
    OrgService service;

    /**
     * 构建下拉树
     *
     * @return
     */
    @GetMapping("/treeSelect")
    @ResponseBody
    public Message treeSelect() {

        QueryWrapper<OrgPo> qw = new QueryWrapper<>();
        List<OrgPo> orgList = service.list(qw);
        List<Map<String, Object>> list = TreeSelectUtil.getTreeSelect(orgList);
        return Message.success(list);

    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, OrgPo po) {

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
            IPage<OrgPo> list = service.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
            return Message.success(list);
        } else {
            List<OrgPo> list = service.list(qw);
            return Message.success(list);
        }

    }

    @GetMapping("/{id}")
    @ResponseBody
    public Message detail(@PathVariable("id") Long id) {

        if (!StringUtils.isEmpty(id)) {
            OrgPo po = service.getById(id);
            return Message.success(po);
        } else {
            return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
        }

    }

    @SysLog("添加组织架构")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody OrgPo po) {

        if (StringUtils.isEmpty(po.getId())) {
            po.initDict();
            for (OrgPo detailPo : po.getDetailList()) {
                detailPo.initDict();
            }
            service.insertSync(po, po.getDetailList(), true);
            return Message.success(po);
        } else {
            return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
        }

    }

    @SysLog("更新组织架构")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody OrgPo po) {

        if (!StringUtils.isEmpty(po.getId())) {
            po.initDict();
            for (OrgPo detailPo : po.getDetailList()) {
                detailPo.initDict();
            }
            service.updateSync(po, po.getDetailList(), true);
            return Message.success(po);
        } else {
            return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
        }

    }

    @SysLog("删除组织架构")
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

}
