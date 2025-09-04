package com.zhora.admin.v1.security.service.impl;

import cn.hutool.core.util.StrUtil;
import com.zhora.admin.v1.security.service.IShiroBusinessService;
import com.zhora.dto.system.UserDetail;
import com.zhora.entity.system.SysUserEntity;
import com.zhora.entity.system.SysUserTokenEntity;
import com.zhora.enums.system.SuperAdminEnum;
import com.zhora.mapper.system.SysMenuMapper;
import com.zhora.mapper.system.SysRoleDataScopeMapper;
import com.zhora.mapper.system.SysUserMapper;
import com.zhora.mapper.system.SysUserTokenMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ShiroBusinessServiceImpl implements IShiroBusinessService {
    private final SysMenuMapper sysMenuDao;
    private final SysUserMapper sysUserDao;
    private final SysUserTokenMapper sysUserTokenDao;
    private final SysRoleDataScopeMapper sysRoleDataScopeDao;

    @Override
    public Set<String> getUserPermissions(UserDetail user) {
        //系统管理员，拥有最高权限
        List<String> permissionsList;
        if (user.getSuperAdmin() == SuperAdminEnum.YES.value()) {
            permissionsList = sysMenuDao.getPermissionsList();
        } else {
            permissionsList = sysMenuDao.getUserPermissionsList(user.getId());
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String permissions : permissionsList) {
            if (StrUtil.isBlank(permissions)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(permissions.trim().split(",")));
        }

        return permsSet;
    }

    @Override
    public SysUserTokenEntity getByToken(String token) {
        return sysUserTokenDao.getByToken(token);
    }

    @Override
    public SysUserEntity getUser(Long userId) {
        return sysUserDao.selectById(userId);
    }

    @Override
    public List<Long> getDataScopeList(Long userId) {
        return sysRoleDataScopeDao.getDataScopeList(userId);
    }
}