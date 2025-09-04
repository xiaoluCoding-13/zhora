package com.zhora.mapper.system;

import com.zhora.entity.system.SysUserTokenEntity;
import com.zhora.mapper.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统用户Token
 * 
 * @author zhehen.lu
 */
@Mapper
public interface SysUserTokenMapper extends BaseDao<SysUserTokenEntity> {

    SysUserTokenEntity getByToken(String token);

    SysUserTokenEntity getByUserId(Long userId);

    void updateToken(@Param("userId") Long userId, @Param("token") String token);
}
