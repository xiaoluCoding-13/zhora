package com.zhora.service.system.impl;

import cn.hutool.core.collection.CollUtil;
import com.zhora.entity.system.SysRoleUserEntity;
import com.zhora.mapper.system.SysRoleUserMapper;
import com.zhora.service.service.impl.BaseServiceImpl;
import com.zhora.service.system.ISysRoleUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色用户关系
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
@Service
public class SysRoleUserServiceImpl extends BaseServiceImpl<SysRoleUserMapper, SysRoleUserEntity> implements ISysRoleUserService {

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        //先删除角色用户关系
        deleteByUserIds(new Long[]{userId});

        //用户没有一个角色权限的情况
        if(CollUtil.isEmpty(roleIdList)){
            return ;
        }

        //保存角色用户关系
        for(Long roleId : roleIdList){
            SysRoleUserEntity sysRoleUserEntity = new SysRoleUserEntity();
            sysRoleUserEntity.setUserId(userId);
            sysRoleUserEntity.setRoleId(roleId);

            //保存
            insert(sysRoleUserEntity);
        }
    }

    @Override
    public void deleteByRoleIds(Long[] roleIds) {
        baseDao.deleteByRoleIds(roleIds);
    }

    @Override
    public void deleteByUserIds(Long[] userIds) {
        baseDao.deleteByUserIds(userIds);
    }

    @Override
    public List<Long> getRoleIdList(Long userId) {

        return baseDao.getRoleIdList(userId);
    }
}