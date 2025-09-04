package com.zhora.admin.v1.security.service.impl;

import com.zhora.admin.v1.security.oauth2.TokenGenerator;
import com.zhora.admin.v1.security.service.ISysUserTokenBusinessService;
import com.zhora.common.domain.ResponseDTO;
import com.zhora.constant.Constant;
import com.zhora.entity.system.SysUserTokenEntity;
import com.zhora.mapper.system.SysUserTokenMapper;
import com.zhora.service.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SysUserTokenBusinessServiceImpl extends BaseServiceImpl<SysUserTokenMapper, SysUserTokenEntity> implements ISysUserTokenBusinessService {
	/**
	 * 12小时后过期
	 */
	private final static int EXPIRE = 3600 * 12;

	@Override
	public ResponseDTO<Map<String, Object>> createToken(Long userId) {
		//用户token
		String token;

		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		SysUserTokenEntity tokenEntity = baseDao.getByUserId(userId);
		if(tokenEntity == null){
			//生成一个token
			token = TokenGenerator.generateValue();

			tokenEntity = new SysUserTokenEntity();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateDate(now);
			tokenEntity.setExpireDate(expireTime);

			//保存token
			this.insert(tokenEntity);
		}else{
			//判断token是否过期
			if(tokenEntity.getExpireDate().getTime() < System.currentTimeMillis()){
				//token过期，重新生成token
				token = TokenGenerator.generateValue();
			}else {
				token = tokenEntity.getToken();
			}

			tokenEntity.setToken(token);
			tokenEntity.setUpdateDate(now);
			tokenEntity.setExpireDate(expireTime);

			//更新token
			this.updateById(tokenEntity);
		}

		Map<String, Object> map = new HashMap<>(2);
		map.put(Constant.TOKEN_HEADER, token);
		map.put("expire", EXPIRE);
		return ResponseDTO.success(map);
	}

	@Override
	public void logout(Long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//修改token
		baseDao.updateToken(userId, token);
	}
}