package com.zhora.admin.v1.system.config.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 参数配置
 * @author zhehen.lu
 * @date 2025/8/28 15:26
 */
@Data
public class SysConfigCheckVO {
    /**
     * 参数主键
     */
    @Schema(description = "参数主键")
    private Long configId;

    /**
     * 参数键名
     */
    @Schema(description = "参数键名")
    private String configKey;
}
