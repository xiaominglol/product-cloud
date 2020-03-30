package com.gemini.business.admin.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.service.impl.BaseDetailServiceImpl;
import com.gemini.business.admin.listener.ExcelListener;
import com.gemini.business.admin.mapper.DictMapper;
import com.gemini.business.admin.po.DictPo;
import com.gemini.business.admin.service.PlatformDictService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 字典表
 *
 * @author 小明不读书
 */
@Service
public class PlatformDictServiceImpl extends BaseDetailServiceImpl<DictPo, DictPo, DictMapper, DictMapper> implements PlatformDictService {

    @Override
    public QueryWrapper<DictPo> wrapper(DictPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getPid()), "pid", po.getPid())
                .eq(!StringUtils.isEmpty(po.getCode()), "code", po.getCode())
                .eq(!StringUtils.isEmpty(po.getName()), "name", po.getName())
                .eq(!StringUtils.isEmpty(po.getDescription()), "description", po.getDescription())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getModifyUserId()), "modify_user_id", po.getModifyUserId())
                .eq(!StringUtils.isEmpty(po.getModifyUserName()), "modify_user_name", po.getModifyUserName())
                .eq(!StringUtils.isEmpty(po.getModifyTime()), "modify_time", po.getModifyTime());
    }

    @Override
    public void upload(MultipartFile file, PlatformDictService service) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, DictPo.class, new ExcelListener(service)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
