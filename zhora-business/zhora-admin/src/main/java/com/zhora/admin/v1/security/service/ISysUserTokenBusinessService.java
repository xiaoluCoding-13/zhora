package com.zhora.admin.v1.security.service;

import com.zhora.common.domain.ResponseDTO;

import java.util.Map;

/**
 * 用户Token
 * 
 * @author zhehen.lu
 */
public interface ISysUserTokenBusinessService {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	ResponseDTO<Map<String, Object>> createToken(Long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(Long userId);

}