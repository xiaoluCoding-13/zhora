package com.zhora.service.system;

import com.zhora.common.domain.page.PageData;
import com.zhora.dto.system.SysUserDTO;
import com.zhora.entity.system.SysUserEntity;
import com.zhora.service.service.BaseService;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 * @author zhehen.lu
 */
public interface ISysUserService extends BaseService<SysUserEntity> {

	PageData<SysUserDTO> page(Map<String, Object> params);

	List<SysUserDTO> list(Map<String, Object> params);

	SysUserDTO get(Long id);

	SysUserDTO getByUsername(String username);

	void save(SysUserDTO dto);

	void update(SysUserDTO dto);

	void delete(Long[] ids);

	/**
	 * 修改密码
	 * @param id           用户ID
	 * @param newPassword  新密码
	 */
	void updatePassword(Long id, String newPassword);

	/**
	 * 根据部门ID，查询用户数
	 */
	int getCountByDeptId(Long deptId);

	/**
	 * 根据部门ID,查询用户Id列表
	 */
	List<Long> getUserIdListByDeptId(List<Long> deptIdList);

}
