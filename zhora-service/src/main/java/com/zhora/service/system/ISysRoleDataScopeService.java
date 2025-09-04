package com.zhora.service.system;

import com.zhora.entity.system.SysRoleDataScopeEntity;
import com.zhora.service.service.BaseService;

import java.util.List;

/**
 * 角色数据权限
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
public interface ISysRoleDataScopeService extends BaseService<SysRoleDataScopeEntity> {

    /**
     * 根据角色ID，获取部门ID列表
     */
    List<Long> getDeptIdList(Long roleId);

    /**
     * 保存或修改
     * @param roleId      角色ID
     * @param deptIdList  部门ID列表
     */
    void saveOrUpdate(Long roleId, List<Long> deptIdList);

    /**
     * 根据角色id，删除角色数据权限关系
     * @param roleId 角色ids
     */
    void deleteByRoleIds(Long[] roleId);
}