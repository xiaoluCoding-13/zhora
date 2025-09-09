package com.zhora.ai.config.ai.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhehen.lu
 * @date 2025/8/22 15:40
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class PgVectorProperties {
    private String url;
    private String username;
    private String password;
}
