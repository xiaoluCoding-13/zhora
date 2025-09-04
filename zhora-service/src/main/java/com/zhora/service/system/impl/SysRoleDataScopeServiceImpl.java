package com.zhora.service.system.impl;

import cn.hutool.core.collection.CollUtil;
import com.zhora.entity.system.SysRoleDataScopeEntity;
import com.zhora.mapper.system.SysRoleDataScopeMapper;
import com.zhora.service.service.impl.BaseServiceImpl;
import com.zhora.service.system.ISysRoleDataScopeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色数据权限
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
@Service
public class SysRoleDataScopeServiceImpl extends BaseServiceImpl<SysRoleDataScopeMapper, SysRoleDataScopeEntity>
        implements ISysRoleDataScopeService {

    @Override
    public List<Long> getDeptIdList(Long roleId) {
        return baseDao.getDeptIdList(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
        //先删除角色数据权限关系
        deleteByRoleIds(new Long[]{roleId});

        //角色没有一个数据权限的情况
        if(CollUtil.isEmpty(deptIdList)){
            return ;
        }

        //保存角色数据权限关系
        for(Long deptId : deptIdList){
            SysRoleDataScopeEntity sysRoleDataScopeEntity = new SysRoleDataScopeEntity();
            sysRoleDataScopeEntity.setDeptId(deptId);
            sysRoleDataScopeEntity.setRoleId(roleId);

            //保存
            insert(sysRoleDataScopeEntity);
        }
    }

    @Override
    public void deleteByRoleIds(Long[] roleIds) {
        baseDao.deleteByRoleIds(roleIds);
    }
}