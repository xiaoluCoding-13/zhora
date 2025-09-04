package com.zhora.mapper.log;

import com.zhora.entity.log.SysLogOperationEntity;
import com.zhora.mapper.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
@Mapper
public interface SysLogOperationDao extends BaseDao<SysLogOperationEntity> {
	
}
