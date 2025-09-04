package com.zhora.service.redis;

import com.zhora.common.config.redis.RedisKeys;
import com.zhora.common.config.redis.RedisUtils;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 参数管理
 *
 * @author zhehen.lu
 * @since 1.0.0
 */
@Component
@AllArgsConstructor
public class SysParamsRedis {

    @Resource
    private RedisUtils redisUtils;

    public void delete(Object[] paramCodes) {
        String key = RedisKeys.getSysParamsKey();
        redisUtils.hDel(key, paramCodes);
    }

    public void set(String paramCode, String paramValue) {
        if (paramValue == null) {
            return;
        }
        String key = RedisKeys.getSysParamsKey();
        redisUtils.hSet(key, paramCode, paramValue);
    }

    public String get(String paramCode) {
        String key = RedisKeys.getSysParamsKey();
        return (String) redisUtils.hGet(key, paramCode);
    }

}
