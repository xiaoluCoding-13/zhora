package com.zhora.dto.system.search;

import java.util.Date;
import java.util.List;

import com.zhora.db.common.dto.PageBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户和角色关联表(sys_user_role)实体类
 *
 * @author zhehen.lu
 * @since 2025-08-27 15:12:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "SysUserRoleSearchDTO", description = "用户和角色关联表信息搜索模型")
public class SysUserRoleSearchDTO extends PageBaseDTO {
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

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private List<Long> userIdList;

}

