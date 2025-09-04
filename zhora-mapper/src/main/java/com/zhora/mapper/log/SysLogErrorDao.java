package com.zhora.mapper.log;

import com.zhora.entity.log.SysLogErrorEntity;
import com.zhora.mapper.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 异常日志
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
@Mapper
public interface SysLogErrorDao extends BaseDao<SysLogErrorEntity> {
	
}
