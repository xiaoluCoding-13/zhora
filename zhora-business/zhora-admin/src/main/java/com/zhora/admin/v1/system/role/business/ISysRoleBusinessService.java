package com.zhora.admin.v1.system.role.business;

import com.zhora.admin.v1.system.role.dto.SysRoleDetailDTO;

import java.util.List;

/**
 * 角色信息表(sys_role)表服务接口
 *
 * @author zhehen.lu
 * @since 2025-08-26 17:49:56
 */
public interface ISysRoleBusinessService {

    List<SysRoleDetailDTO> getRolesByUserId(Long userId);

}
