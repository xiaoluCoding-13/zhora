package com.zhora.entity.system;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色信息表(sys_role)实体类
 *
 * @author zhehen.lu
 * @since 2025-08-26 17:01:36
 */
@Data
@TableName(value = "sys_role")
public class SysRoleEntity implements Serializable {
    private static final long serialVersionUID = -20958474213479431L;
    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.ASSIGN_ID)
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 角色权限字符串
     */
    @TableField(value = "role_key")
    private String roleKey;

    /**
     * 显示顺序
     */
    @TableField(value = "role_sort")
    private Integer roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    @TableField(value = "data_scope")
    private String dataScope;

    /**
     * 角色状态（0正常 1停用）
     */
    @TableField(value = "status")
    private String status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 删除标记,true:已删除,false:正常
     */
    @TableLogic
    @TableField(value = "del_flag")
    private Boolean delFlag;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}

