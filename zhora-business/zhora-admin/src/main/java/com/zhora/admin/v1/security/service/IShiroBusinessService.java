package com.zhora.admin.v1.security.service;

import com.zhora.dto.system.UserDetail;
import com.zhora.entity.system.SysUserEntity;
import com.zhora.entity.system.SysUserTokenEntity;

import java.util.List;
import java.util.Set;

/**
 * shiro相关接口
 *
 * @author zhehen.lu
 */
public interface IShiroBusinessService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(UserDetail user);

    SysUserTokenEntity getByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUserEntity getUser(Long userId);

    /**
     * 获取用户对应的部门数据权限
     * @param userId  用户ID
     * @return        返回部门ID列表
     */
    List<Long> getDataScopeList(Long userId);
}
