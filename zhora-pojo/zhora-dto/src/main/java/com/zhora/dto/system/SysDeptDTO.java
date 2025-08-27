package com.zhora.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 部门表(sys_dept)实体类
 *
 * @author zhehen.lu
 * @since 2025-08-26 16:43:57
 */
@Data
@Schema(name = "SysDeptDTO",description = "部门信息模型")
public class SysDeptDTO {
    /**
     * 部门id
     */
    @Schema(description = "部门id")
    private Long deptId;

    /**
     * 父部门id
     */
    @Schema(description = "父部门id")
    private Long parentId;

    /**
     * 祖级列表
     */
    @Schema(description = "祖级列表")
    private String ancestors;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer orderNum;

    /**
     * 负责人
     */
    @Schema(description = "负责人")
    private String leader;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 部门状态（0正常 1停用）
     */
    @Schema(description = "部门状态（0正常 1停用）")
    private String status;

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

