package com.zhora.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhora.common.dto.page.PageDataGridRespDTO;
import com.zhora.dto.system.SysRoleDTO;
import com.zhora.dto.system.search.SysRoleSearchDTO;
import com.zhora.entity.system.SysRoleEntity;

import java.util.List;

/**
 * 角色信息表(sys_role)表服务接口
 *
 * @author zhehen.lu
 * @since 2025-08-26 17:49:56
 */
public interface ISysRoleService extends IService<SysRoleEntity> {

    PageDataGridRespDTO<SysRoleDTO> listPage(SysRoleSearchDTO searchDTO);

    void create(SysRoleDTO dto);

    SysRoleDTO getDetailById(Long id);

    SysRoleDTO getDetail(SysRoleSearchDTO searchDTO);

    void updateById(SysRoleDTO dto);

    List<SysRoleDTO> list(SysRoleSearchDTO searchDTO);
}
