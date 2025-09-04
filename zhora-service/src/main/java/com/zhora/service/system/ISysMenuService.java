package com.zhora.service.system;

import com.zhora.dto.system.SysMenuDTO;
import com.zhora.dto.system.UserDetail;
import com.zhora.entity.system.SysMenuEntity;
import com.zhora.service.service.BaseService;

import java.util.List;


/**
 * 菜单管理
 * 
 * @author zhehen.lu
 */
public interface ISysMenuService extends BaseService<SysMenuEntity> {

	SysMenuDTO get(Long id);

	void save(SysMenuDTO dto);

	void update(SysMenuDTO dto);

	void delete(Long id);

	/**
	 * 菜单列表
	 *
	 * @param menuType 菜单类型
	 */
	List<SysMenuDTO> getAllMenuList(Integer menuType);

	/**
	 * 用户菜单列表
	 *
	 * @param user  用户
	 * @param menuType 菜单类型
	 */
	List<SysMenuDTO> getUserMenuList(UserDetail user, Integer menuType);

	/**
	 * 根据父菜单，查询子菜单
	 * @param pid  父菜单ID
	 */
	List<SysMenuDTO> getListPid(Long pid);
}
