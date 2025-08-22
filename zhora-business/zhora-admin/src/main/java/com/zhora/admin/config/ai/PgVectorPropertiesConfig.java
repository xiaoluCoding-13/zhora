
package com.zhora.admin.config.ai;

import com.zhora.admin.config.ai.properties.PgVectorProperties;
import com.zhora.admin.config.ai.properties.StoreProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhehen.lu
 * @date 2025/8/22 15:39
 */
@Configuration
public class PgVectorPropertiesConfig {

    private final PgVectorProperties pgVectorProperties;

    public PgVectorPropertiesConfig(PgVectorProperties pgVectorProperties) {
        this.pgVectorProperties = pgVectorProperties;
    }
}
