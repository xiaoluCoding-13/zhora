package com.zhora.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhora.entity.system.SysRoleEntity;

import java.util.List;

/**
 * 角色信息表(sys_role)表数据库访问层
 *
 * @author zhehen.lu
 * @since 2025-08-26 17:49:18
 */
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<SysRoleEntity> selectRolesByUserId(Long userId);

}

