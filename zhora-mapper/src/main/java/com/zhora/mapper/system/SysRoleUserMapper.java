package com.zhora.mapper.system;

import com.zhora.entity.system.SysRoleUserEntity;
import com.zhora.mapper.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色用户关系
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
@Mapper
public interface SysRoleUserMapper extends BaseDao<SysRoleUserEntity> {

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
     *
     * @return
     */
    List<Long> getRoleIdList(Long userId);
}