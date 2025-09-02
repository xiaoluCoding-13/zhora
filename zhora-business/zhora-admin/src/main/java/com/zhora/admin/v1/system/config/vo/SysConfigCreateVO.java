package com.zhora.admin.v1.system.config.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 参数配置
 *
 * @author zhehen.lu
 * @date 2025/8/28 15:24
 */
@Data
public class SysConfigCreateVO {

    /**
     * 参数名称
     */
    @Schema(description = "参数名称")
    private String configName;

    /**
     * 参数键名
     */
    @Schema(description = "参数键名")
    private String configKey;

    /**
     * 参数键值
     */
    @Schema(description = "参数键值")
    private String configValue;

    /**
     * 系统内置（Y是 N否）
     */
    @Schema(description = "系统内置（Y是 N否）")
    private String configType;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
}
