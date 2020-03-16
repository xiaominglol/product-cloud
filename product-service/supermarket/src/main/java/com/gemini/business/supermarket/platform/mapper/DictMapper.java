package com.gemini.business.supermarket.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.business.supermarket.platform.po.DictPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典表
 *
 * @author 小明不读书
 */
@Mapper
public interface DictMapper extends BaseMapper<DictPo> {
}
