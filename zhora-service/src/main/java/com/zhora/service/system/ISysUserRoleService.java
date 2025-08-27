package com.zhora.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import com.zhora.dto.system.SysUserRoleDTO;
import com.zhora.dto.system.search.SysUserRoleSearchDTO;
import com.zhora.entity.system.SysUserRoleEntity;

import java.util.List;

/**
 * 用户和角色关联表(sys_user_role)表服务接口
 *
 * @author zhehen.lu
 * @since 2025-08-27 15:28:01
 */
public interface ISysUserRoleService extends IService<SysUserRoleEntity> {

    PageDataGridRespDTO<SysUserRoleDTO> listPage(SysUserRoleSearchDTO searchDTO);

    void create(SysUserRoleDTO dto);

    SysUserRoleDTO getDetailByUserId(Long id);

    SysUserRoleDTO getDetailByRoleId(Long id);

    SysUserRoleDTO getDetail(SysUserRoleSearchDTO searchDTO);

    void updateById(SysUserRoleDTO dto);

    List<SysUserRoleDTO> list(SysUserRoleSearchDTO searchDTO);
}
