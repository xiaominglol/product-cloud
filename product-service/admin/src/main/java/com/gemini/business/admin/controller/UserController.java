package com.gemini.business.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.shiro.utils.MD5Util;
import com.gemini.boot.framework.web.entity.CommonFailInfo;
import com.gemini.boot.framework.web.entity.Message;
import com.gemini.boot.framework.web.utils.DateUtils;
import com.gemini.boot.framework.web.utils.ExcelImportUtils;
import com.gemini.business.admin.common.annotation.SysLog;
import com.gemini.business.admin.common.properties.GeminiProperties;
import com.gemini.business.admin.common.utils.Poi314ExcelUtils;
import com.gemini.business.admin.po.UserPo;
import com.gemini.business.admin.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 小明不读书
 * @date 2017-11-03
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    UserService service;
    @Autowired
    GeminiProperties geminiProperties;


    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, UserPo po) {

        QueryWrapper<UserPo> qw = new QueryWrapper<>();
        if (!StringUtils.isEmpty(po.getName())) {
            qw.like("name", po.getName()).or().like("account", po.getName());
        }
        if (!StringUtils.isEmpty(po.getOrgId())) {
            qw.eq("org_id", po.getOrgId());
        }
        if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
            IPage<UserPo> list = service.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
            return Message.success(list);
        } else {
            List<UserPo> list = service.list(qw);
            return Message.success(list);
        }

    }

    @GetMapping("/{id}")
    @ResponseBody
    public Message detail(@PathVariable("id") Long id) {

        if (!StringUtils.isEmpty(id)) {
            UserPo user = service.getById(id);
            return Message.success(user);
        } else {
            return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
        }

    }

    @SysLog("添加用户")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody UserPo po) {

        if (StringUtils.isEmpty(po.getId())) {
            String pwd = MD5Util.encryption(po.getPassword(), po.getAccount());
            po.setPassword(pwd);
            service.insertSync(po, po.getDetailList(), false);
            return Message.success(po);
        } else {
            return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
        }

    }

    @SysLog("批量添加用户")
    @PostMapping("/batchSave")
    @ResponseBody
    public Message batchAdd(@RequestBody(required = false) List<UserPo> userList) {

        if (userList != null && userList.size() > 0) {
            for (UserPo po : userList) {
                String pwd = MD5Util.encryption(MD5Util.INIT_PASSWORD, po.getAccount());
                po.setPassword(pwd);
                po.setPicture("/img/icon/64/default_picture.png");
                service.insertSync(po, po.getDetailList(), false);
            }
            return Message.success(null);
        } else {
            return Message.fail(CommonFailInfo.USER_LIST_CAN_NOT_BE_EMPTY);
        }

    }

    @SysLog("更新用户")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody UserPo po) {

        if (!StringUtils.isEmpty(po.getId())) {
            UserPo oldUser = service.getById(po.getId());
            //如果密码不一样，证明在修改密码，所以要加密保存，如果密码一样，则不需要再加密,因为前端传过来的密码已经加密过了
            if (!po.getPassword().equals(oldUser.getPassword())) {
                String pwd = MD5Util.encryption(po.getPassword(), po.getAccount());
                po.setPassword(pwd);
            }
            service.updateSync(po, po.getDetailList(), false);
            return Message.success(po);
        } else {
            return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
        }

    }

    @SysLog("删除用户")
    @DeleteMapping("/{ids}")
    @ResponseBody
    public Message delete(@PathVariable("ids") Long[] ids) {

        if (!StringUtils.isEmpty(ids)) {
            for (Long id : ids) {
                service.deleteByIdSync(id);
            }
            return Message.success(null);
        } else {
            return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
        }

    }

    /**
     * 获得用户角色
     *
     * @param userId 用户账号
     * @return
     */
    @GetMapping("/userRole/{userId}")
    @ResponseBody
    public Message getUserRole(@PathVariable("userId") Long userId) {

        if (!StringUtils.isEmpty(userId)) {
            List<Map<String, String>> list = service.getUserRole(userId);
            List<String> idList = new ArrayList<>();
            for (Map<String, String> map : list) {
                idList.add(map.get("roleId"));
            }
            return Message.success(idList);
        } else {
            return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
        }

    }

    /**
     * 上传图片
     */
    @PostMapping("/picture/upload")
    @ResponseBody
    public Message upload(@RequestPart("file") MultipartFile picture) {
        if (picture.isEmpty() || StringUtils.isEmpty(picture.getOriginalFilename())) {
            return Message.fail(CommonFailInfo.FILE_CAN_NOT_BE_EMPTY);
        }
        String pictureName = picture.getOriginalFilename();

        String fileSavePath = geminiProperties.getFileUploadPath();
        try {
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return Message.success(pictureName);

    }

    /**
     * 用户导入
     *
     * @param file
     * @return
     * @throws IOException
     */
    @SysLog("用户导入")
    @PostMapping("/import")
    @ResponseBody
    public Message batchImport(@RequestParam(value = "file") MultipartFile file) throws IOException {

        //判断文件是否为空
        if (file == null) {
            return Message.fail(CommonFailInfo.FILE_CAN_NOT_BE_EMPTY);
        }

        //获取文件名
        String fileName = file.getOriginalFilename();

        //验证文件名是否合格
        if (!ExcelImportUtils.validateExcel(fileName)) {
            return Message.fail(CommonFailInfo.FILE_MUST_BE_EXCEL_FORMAT);
        }

        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        long size = file.getSize();
        if (StringUtils.isEmpty(fileName) || size == 0) {
            return Message.fail(CommonFailInfo.FILE_CONTENT_CAN_NOT_BE_EMPTY);
        }

//            if (!fileName.matches(Poi314ExcelUtils.xls) && !fileName.matches(Poi314ExcelUtils.xlsx)) {
//                return Message.fail(CommonFailInfo.FILE_FORMAT_INCORRECT);
//            }
        boolean isExcel2003 = true;
//            if (fileName.matches(Poi314ExcelUtils.xlsx)) {
//                isExcel2003 = false;
//            }
        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Workbook wb;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        UserPo user;
        List<UserPo> userList = new ArrayList<>();
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            StringBuffer sb = new StringBuffer();
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }

            user = new UserPo();

            //用户账号
            if (StringUtils.isEmpty(row.getCell(0))) {
                sb.append("用户账号不能为空。");
            } else {
                if (row.getCell(0).getCellType() != 1) {
//                        .error("导入失败(第" + (r + 1) + "行,姓名请设为文本格式)");
                }
                String account = row.getCell(0).getStringCellValue();
                user.setAccount(account);
            }

            //用户名称
            if (StringUtils.isEmpty(row.getCell(1))) {
                sb.append("用户名称不能为空。");
            } else {
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                String name = row.getCell(1).getStringCellValue();
                user.setName(name);
            }
            if (!StringUtils.isEmpty(sb.toString())) {
//                    user.setImportStatus(sb.toString());
            }
            userList.add(user);
        }
        return Message.success(userList);

    }

    /**
     * 用户导出
     *
     * @return
     */
    @SysLog("用户导出")
    @GetMapping("/export")
    public void export(HttpServletResponse response) {

        // 第一步：定义文件名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String fileName = "用户导出-" + sdf.format(new Date()) + ".xlsx";
        // 第二步：定义工作簿名称
        String sheetName = "用户导出";
        // 第三步：设置表头名称
        String[] headNames = {"用户账号", "用户名称", "创建时间"};
        // 第四步：设置字段名称
        String[] fieldNames = {"account", "name", "createDate"};
        // 第五步：设置列宽
        Short[] columnWidths = {5000, 6000, 7000};
        // 第六步：组装数据
        List<Map<String, Object>> dataList = new ArrayList<>();
        List<UserPo> list = service.list(service.wrapper());
        for (UserPo user : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("account", user.getAccount());
            map.put("name", user.getName());
            map.put("createDate", DateUtils.getDateTime(user.getCreateTime()));
            dataList.add(map);
        }
        // 第七步：调用工具类
        Poi314ExcelUtils.exportExcel(fileName, sheetName, headNames, fieldNames, columnWidths, dataList, response, Poi314ExcelUtils.EXCEL_2010, -1);


    }

    /**
     * 重置密码
     *
     * @param userList 用户列表
     * @return
     */
    @SysLog("重置密码")
    @PutMapping("/restPwd")
    @ResponseBody
    public Message restPwd(@RequestBody List<UserPo> userList) {

        if (userList != null && userList.size() > 0) {
            for (UserPo user : userList) {
                String pwd = MD5Util.encryption(MD5Util.INIT_PASSWORD, user.getAccount());
                user.setPassword(pwd);
                service.updateSync(user, false);
            }
            return Message.success(null);
        } else {
            return Message.fail(CommonFailInfo.USER_LIST_CAN_NOT_BE_EMPTY);
        }

    }

}
