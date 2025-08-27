package com.zhora.admin.v1.system.dept.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 部门表(sys_dept)实体类
 *
 * @author zhehen.lu
 * @since 2025-08-26 16:43:57
 */
@Data
@Schema(name = "SysDeptDetailDTO",description = "部门信息详情响应模型")
public class SysDeptDetailDTO {
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
}

