package com.gemini.business.admin.service;

import com.gemini.boot.framework.mybatis.service.BaseDetailService;
import com.gemini.business.admin.mapper.DictMapper;
import com.gemini.business.admin.po.DictPo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 字典表
 *
 * @author 小明不读书
 */
public interface PlatformDictService extends BaseDetailService<DictPo, DictPo, DictMapper, DictMapper> {
    void upload(MultipartFile file, PlatformDictService service);
}
