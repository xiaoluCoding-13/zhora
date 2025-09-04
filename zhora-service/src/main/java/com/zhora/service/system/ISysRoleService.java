package com.zhora.service.system;

import com.zhora.common.domain.page.PageData;
import com.zhora.dto.system.SysRoleDTO;
import com.zhora.entity.system.SysRoleEntity;
import com.zhora.service.service.BaseService;

import java.util.List;
import java.util.Map;


/**
 * 角色
 * 
 * @author zhehen.lu
 */
public interface ISysRoleService extends BaseService<SysRoleEntity> {

	PageData<SysRoleDTO> page(Map<String, Object> params);

	List<SysRoleDTO> list(Map<String, Object> params);

	SysRoleDTO get(Long id);

	void save(SysRoleDTO dto);

	void update(SysRoleDTO dto);

	void delete(Long[] ids);

}
