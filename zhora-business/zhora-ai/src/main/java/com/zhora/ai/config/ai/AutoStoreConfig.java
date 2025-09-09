package com.zhora.ai.config.ai;

import com.zhora.ai.config.ai.properties.StoreProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhehen.lu
 * @date 2025/8/22 15:39
 */
@Configuration
public class AutoStoreConfig {

    private final StoreProperties storeProperties;

    public AutoStoreConfig(StoreProperties storeProperties) {
        this.storeProperties = storeProperties;
    }
}
