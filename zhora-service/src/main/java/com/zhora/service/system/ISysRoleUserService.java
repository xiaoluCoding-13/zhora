package com.zhora.service.system;

import com.zhora.entity.system.SysRoleUserEntity;
import com.zhora.service.service.BaseService;

import java.util.List;

/**
 * 角色用户关系
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
public interface ISysRoleUserService extends BaseService<SysRoleUserEntity> {

    /**
     * 保存或修改
     * @param userId      用户ID
     * @param roleIdList  角色ID列表
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据角色ids，删除角色用户关系
     * @param roleIds 角色ids
     */
    void deleteByRoleIds(Long[] roleIds);

    /**
     * 根据用户id，删除角色用户关系
     * @param userIds 用户ids
     */
    void deleteByUserIds(Long[] userIds);

    /**
     * 角色ID列表
     * @param userId  用户ID
     */
    List<Long> getRoleIdList(Long userId);
}