package com.gemini.business.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.admin.po.OptLogPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志表
 *
 * @author 小明不读书
 */
@Mapper
public interface OptLogMapper extends BaseMapper<OptLogPo> {
}
