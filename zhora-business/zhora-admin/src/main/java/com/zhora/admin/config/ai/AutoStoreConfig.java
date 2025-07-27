package com.zhora.admin.config.ai;

import com.zhora.admin.config.ai.properties.StoreProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhehen.lu
 * @date 2025/7/27 15:39
 */
@Configuration
public class AutoStoreConfig {

    private final StoreProperties storeProperties;

    public AutoStoreConfig(StoreProperties storeProperties) {
        this.storeProperties = storeProperties;
    }
}
