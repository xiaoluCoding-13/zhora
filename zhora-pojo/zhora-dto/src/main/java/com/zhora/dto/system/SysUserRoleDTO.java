package com.zhora.dto.system;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户和角色关联表(sys_user_role)实体类
 *
 * @author zhehen.lu
 * @since 2025-08-27 15:12:16
 */
@Data
@Schema(name = "SysUserRoleDTO", description = "用户和角色关联表信息模型")
public class SysUserRoleDTO {
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private Long roleId;

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

