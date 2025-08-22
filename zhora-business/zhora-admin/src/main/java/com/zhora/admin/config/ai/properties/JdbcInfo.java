package com.zhora.admin.config.ai.properties;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhehen.lu
 * @date 2025/8/22 21:13
 */
@Data
public class JdbcInfo {
    private String host;
    private int port;
    private String database;
}
