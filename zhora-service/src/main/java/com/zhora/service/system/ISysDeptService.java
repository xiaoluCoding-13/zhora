package com.zhora.service.system;

import com.zhora.dto.system.SysDeptDTO;
import com.zhora.entity.system.SysDeptEntity;
import com.zhora.service.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author zhehen.lu
 */
public interface ISysDeptService extends BaseService<SysDeptEntity> {

	List<SysDeptDTO> list(Map<String, Object> params);

	SysDeptDTO get(Long id);

	void save(SysDeptDTO dto);

	void update(SysDeptDTO dto);

	void delete(Long id);

	/**
	 * 根据部门ID，获取本部门及子部门ID列表
	 * @param id   部门ID
	 */
	List<Long> getSubDeptIdList(Long id);
}