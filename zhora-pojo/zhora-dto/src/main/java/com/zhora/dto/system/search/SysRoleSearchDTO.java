package com.zhora.dto.system.search;

import com.zhora.db.common.dto.PageBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 角色信息表(sys_role)实体类
 *
 * @author zhehen.lu
 * @since 2025-08-26 17:01:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "SysRoleDTO",description = "角色信息搜索模型")
public class SysRoleSearchDTO extends PageBaseDTO {
    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private Long roleId;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String roleName;

    /**
     * 角色权限字符串
     */
    @Schema(description = "角色权限字符串")
    private String roleKey;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    @Schema(description = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
    private String dataScope;

    /**
     * 角色状态（0正常 1停用）
     */
    @Schema(description = "角色状态（0正常 1停用）")
    private String status;

    /** 备注 */
    @Schema(description = "备注")
    private String remark;

    /**
     * 删除标记,true:已删除,false:正常
     */
    @Schema(description = "删除标记,true:已删除,false:正常")
    private Boolean delFlag;

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private List<Long> roleIdList;

}

