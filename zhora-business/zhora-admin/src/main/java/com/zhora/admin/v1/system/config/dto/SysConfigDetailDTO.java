package com.zhora.admin.v1.system.config.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 参数配置
 *
 * @author zhehen.lu
 * @date 2025/8/28 15:29
 */
@Data
public class SysConfigDetailDTO {
    /**
     * 参数主键
     */
    @Schema(description = "参数主键")
    private Long configId;

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

    /**
     * 删除标记,true:已删除,false:正常
     */
    @Schema(description = "删除标记,true:已删除,false:正常")
    private Boolean delFlag;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;
}
