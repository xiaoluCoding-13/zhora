package com.zhora.admin.v1.system.config.business.impl;

import com.zhora.admin.v1.system.config.business.ISysConfigBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RuoYi首创 html调用 thymeleaf 实现参数管理
 * 
 * @author ruoyi
 */
@Service("config")
public class ConfigService
{
    @Autowired
    private ISysConfigBusinessService configBusinessService;

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey 参数键名
     * @return 参数键值
     */
    public String getKey(String configKey)
    {
        return configBusinessService.selectConfigByKey(configKey);
    }
}
