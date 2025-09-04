package com.zhora.mapper.log;

import com.zhora.entity.log.SysLogLoginEntity;
import com.zhora.mapper.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
@Mapper
public interface SysLogLoginDao extends BaseDao<SysLogLoginEntity> {
	
}
