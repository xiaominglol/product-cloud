package com.gemini.business.education.lecturer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.education.lecturercation.lecturer.po.EduCourseCollectPo;
import com.gemini.business.education.lecturercation.lecturer.service.EduCourseCollectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程收藏
 *
 * @author 小明不读书
 * @date Fri Mar 27 22:22:37 CST 2020
 */
@Slf4j
@RestController
@RequestMapping("/edu/course/collect")
public class EduCourseCollectController {

    @Autowired
    EduCourseCollectService service;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "edu/_list";
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, EduCourseCollectPo po) {
        QueryWrapper<EduCourseCollectPo> qw = new QueryWrapper<>();
        if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
            IPage<EduCourseCollectPo> list = service.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
            return Message.success(list);
        } else {
            List<EduCourseCollectPo> list = service.list(qw);
            return Message.success(list);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Message detail(@PathVariable("id") Long id) {
        if (!StringUtils.isEmpty(id)) {
            EduCourseCollectPo eduCourseCollectPo = service.getById(id);
            return Message.success(eduCourseCollectPo);
        } else {
            return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
        }
    }

    @PostMapping
    @ResponseBody
    public Message add(@RequestBody EduCourseCollectPo po) {
        if (StringUtils.isEmpty(po.getId())) {
            service.insertSync(po, false);
            return Message.success(po);
        } else {
            return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
        }
    }

    @PutMapping
    @ResponseBody
    public Message update(@RequestBody EduCourseCollectPo po) {
        if (!StringUtils.isEmpty(po.getId())) {
            service.updateSync(po, false);
            return Message.success(po);
        } else {
            return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
        }
    }

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
